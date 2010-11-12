package at.ticketline.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import at.ticketline.dao.jpa.Data;
import at.ticketline.entity.Platz;

/**
 * Abstrakte Testklasse fuer DAOs
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public class AbstractDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Liefert die aktuelle SQL-Connection zurueck
     * 
     */
    protected Connection getSqlConnection() {
        DataSource dataSource = ((JdbcTemplate) this.simpleJdbcTemplate.getJdbcOperations()).getDataSource();
        return DataSourceUtils.getConnection(dataSource);
    }

    /**
     * Liefert die aktuelle SQL-Connection fuer DbUnit zurueck
     * 
     */
    protected IDatabaseConnection getDbUnitConnection() {
        try {
            return new DatabaseConnection(this.getSqlConnection());
        } catch (DatabaseUnitException due) {
            throw new RuntimeException(due);
        }
    }

    /**
     * Liefert eine in applicationContext.xml definierte Bean zurueck
     * 
     * @param name ID der Bean in applicationContext.xml
     * @return die Bean oder <code>null</code>
     */
    protected Object getBean(String name) {
        return this.applicationContext.getBean(name);
    }

    /**
     * Liefert den aktuellen EntityManager zurueck
     * 
     */
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    protected boolean resultIsOneOf(List<Platz> result, List<List<Integer>> expected) {
        ex: for (List<Integer> ex : expected) {
            if (result.size() != ex.size())
                continue;
            ids: for (Integer id : ex) {
                for (Platz p : result)
                    if (p.getId().equals(id))
                        continue ids;
                continue ex;
            }
            return true;
        }
        return false;
    }

    protected boolean resultContainsOneOf(List<Platz> list, List<Integer> ids) {
        for (Integer id : ids) {
            for (Platz p : list)
                if (p.getId().equals(id)) {
                    return true;
                }
        }

        return false;
    }

    protected List<Data> loadTestfile(String testfile) {
        List<Data> data = new ArrayList<Data>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(testfile));
            String input = "";
            while ((input = reader.readLine()) != null && !input.equals("")) {
                String[] split = input.split(":");
                List<Integer> inputList = new ArrayList<Integer>();
                List<List<Integer>> outputList = new ArrayList<List<Integer>>();

                // [0]: input
                String[] eingabe = split[0].split(",");
                for (String in : eingabe) {
                    inputList.add(new Integer(in));
                }

                // [>0]: output
                for (int i = 1; i < split.length; i++) {
                    String[] ausgabe = split[i].split(",");
                    List<Integer> list = new ArrayList<Integer>();
                    for (String out : ausgabe) {
                        list.add(new Integer(out));
                    }
                    outputList.add(list);
                }

                data.add(new Data(inputList, outputList));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return data;
    }
}
