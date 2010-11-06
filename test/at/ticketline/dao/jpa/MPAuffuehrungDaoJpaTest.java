package at.ticketline.dao.jpa;

import java.io.FileReader;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import at.ticketline.dao.AbstractDaoTest;
import at.ticketline.dao.interfaces.AuffuehrungDao;
import at.ticketline.entity.Platz;

/*
 * @author: Michael Petritsch
 */
public class MPAuffuehrungDaoJpaTest extends AbstractDaoTest {
    IDatabaseConnection dbUnitCon = null;

    @Autowired
    private AuffuehrungDao auffuehrungDao;

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
    public void testFindBest() {
        loadData("mp_dataset.xml");

        int[] ids = new int[] { 1, 2 };

        List<Platz> result = auffuehrungDao.findBest(2);
        for (Platz a : result) {
            System.out.println(a.getId());
        }
        // Assert.assertTrue(resultContainsExactly(result, ids));
    }

    // @Test
    // public void testFindBestCount() {
    // loadData("mp_dataset.xml");
    //
    // int[] ids1 = new int[] { 1, 2 };
    // int[] ids2 = new int[] { 2, 3 };
    //
    // List<Platz> result = auffuehrungDao.findBest(1, 2);
    //
    // Assert.assertTrue(resultContainsExactly(result, ids1) ||
    // resultContainsExactly(result, ids2));
    // }
    //
    // @Test
    // public void findBest1CountMaxPriceBest() {
    // loadData("mp_dataset.xml");
    //
    // int[] ids1 = new int[] { 1, 2 };
    // int[] ids2 = new int[] { 2, 3 };
    //
    // List<Platz> result = auffuehrungDao.findBest(1, 2, new BigDecimal(150));
    //
    // Assert.assertTrue(resultContainsExactly(result, ids1) ||
    // resultContainsExactly(result, ids2));
    // }
    //
    // @Test
    // public void findCheapest2Cheapest() {
    // loadData("mp_dataset.xml");
    //
    // int[] ids1 = new int[] { 4, 5, 6 };
    //
    // List<Platz> result = auffuehrungDao.findCheapest(1);
    // System.out.println(result);
    // Assert.assertTrue(resultContainsExactly(result, ids1));
    // }
    //
    // @Test
    // public void findCheapest2CheapestCount() {
    // loadData("mp_dataset.xml");
    //
    // int[] ids1 = new int[] { 4, 5 };
    // int[] ids2 = new int[] { 5, 6 };
    //
    // List<Platz> result = auffuehrungDao.findCheapest(1, 2);
    // System.out.println(result);
    // Assert.assertTrue(resultContainsExactly(result, ids1) ||
    // resultContainsExactly(result, ids2));
    // }

    @After
    public void teardown() {
        try {
            dbUnitCon.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
