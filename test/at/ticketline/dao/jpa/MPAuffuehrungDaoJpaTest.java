package at.ticketline.dao.jpa;

import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import at.ticketline.dao.AbstractDaoTest;
import at.ticketline.entity.Platz;

/*
 * @author: Michael Petritsch
 */
public class MPAuffuehrungDaoJpaTest extends AbstractDaoTest {
    IDatabaseConnection dbUnitCon = null;

    @BeforeTransaction
    public void beforeTransaction() {
        System.out.println("Before Transaction");
    }

    @AfterTransaction
    public void afterTransaction() {
        System.out.println("After Transaction");
    }

    @Before
    public void setup() {
        dbUnitCon = this.getDbUnitConnection();
    }

    public void loadData(String datasetFileName) {
        try {
            IDataSet dataSet = new XmlDataSet(new FileReader(datasetFileName));
            // DatabaseOperation.INSERT.execute(dbUnitCon, dataSet);
            DatabaseOperation.CLEAN_INSERT.execute(dbUnitCon, dataSet);
            DatabaseOperation.REFRESH.execute(dbUnitCon, dataSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findBestWrongIdTest() {
        loadData("mp_dataset.xml");

        Boolean hasResult = false;
        List<Data> data = loadTestfile("mp_findBest1");
        for (Data d : data) {
            hasResult = true;
            try {
                List<Platz> result = auffuehrungDao.findBest(d.getInput().get(0));
            } catch (Exception e) {
                continue;
            }
            Assert.assertTrue(false);
        }
        Assert.assertTrue(hasResult);
    }

    @Test
    public void findBestCountWrongIdTest() {
        loadData("mp_dataset.xml");

        Boolean hasResult = false;
        List<Data> data = loadTestfile("mp_findBest2");

        for (Data d : data) {
            hasResult = true;
            try {
                List<Platz> result = auffuehrungDao.findBest(d.getInput().get(0), d.getInput().get(1));
            } catch (Exception e) {
                continue;
            }
            Assert.assertTrue(false);
        }
        Assert.assertTrue(hasResult);
    }

    @Test
    public void findBestCountMaxPriceWrongIdTest() {
        loadData("mp_dataset.xml");

        Boolean hasResult = false;
        List<Data> data = loadTestfile("mp_findBest3");

        for (Data d : data) {
            hasResult = true;
            try {
                List<Platz> result = auffuehrungDao.findBest(d.getInput().get(0), d.getInput().get(1), new BigDecimal(d
                        .getInput().get(2)));
            } catch (Exception e) {
                continue;
            }
            Assert.assertTrue(false);
        }
        Assert.assertTrue(hasResult);
    }

    @Test
    public void findCheapestWrongIdTest() {
        loadData("mp_dataset.xml");

        Boolean hasResult = false;
        List<Data> data = loadTestfile("mp_findCheapest1");

        for (Data d : data) {
            hasResult = true;
            try {
                List<Platz> result = auffuehrungDao.findCheapest(d.getInput().get(0));
            } catch (Exception e) {
                continue;
            }
            Assert.assertTrue(false);
        }
        Assert.assertTrue(hasResult);
    }

    @Test
    public void findCheapestCountWrongIdTest() {
        loadData("mp_dataset.xml");

        Boolean hasResult = false;
        List<Data> data = loadTestfile("mp_findCheapest2");

        for (Data d : data) {
            hasResult = true;
            try {
                List<Platz> result = auffuehrungDao.findCheapest(d.getInput().get(0), d.getInput().get(1));
            } catch (Exception e) {
                continue;
            }
            Assert.assertTrue(false);
        }
        Assert.assertTrue(hasResult);
    }

    @After
    public void teardown() {
        try {
            dbUnitCon.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
