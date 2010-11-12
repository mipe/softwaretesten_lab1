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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import at.ticketline.dao.AbstractDaoTest;
import at.ticketline.dao.interfaces.AuffuehrungDao;
import at.ticketline.entity.Platz;

public class AuffuehrungDaoJpaTest extends AbstractDaoTest {
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

    /*
     * 0626629 Dominik Hofer
     */
    @Test
    public void dhFindBestSomeTest() {
        loadData("dh_dataset.xml");

        List<Data> data = loadTestfile("dh_findBestSome");
        for (Data d : data) {
            List<List<Integer>> expected = d.getOutput();
            List<Platz> result = auffuehrungDao.findBest(d.getInput().get(0));
            Assert.assertTrue(resultIsOneOf(result, expected));
        }
    }

    /*
     * 0626629 Dominik Hofer
     */
    @Test
    public void dhFindBestCountSomeTest() {
        loadData("dh_dataset.xml");

        List<Data> data = loadTestfile("dh_findBestCountSome");

        for (Data d : data) {
            List<List<Integer>> expected = d.getOutput();
            List<Platz> result = auffuehrungDao.findBest(d.getInput().get(0), d.getInput().get(1));
            Assert.assertTrue(resultIsOneOf(result, expected));
        }
    }

    /*
     * 0626629 Dominik Hofer
     */
    @Test
    public void dhFindBestCountMaxPriceSomeTest() {
        loadData("dh_dataset.xml");

        List<Data> data = loadTestfile("dh_findBestCountMaxPriceSome");

        for (Data d : data) {
            List<List<Integer>> expected = d.getOutput();
            List<Platz> result = auffuehrungDao.findBest(d.getInput().get(0), d.getInput().get(1), new BigDecimal(d
                    .getInput().get(2)));
            Assert.assertTrue(resultIsOneOf(result, expected));
        }
    }

    /*
     * 0626629 Dominik Hofer
     */
    @Test
    public void findCheapestSomeTest() {
        loadData("dh_dataset.xml");

        List<Data> data = loadTestfile("dh_findCheapestSome");

        for (Data d : data) {
            List<List<Integer>> expected = d.getOutput();
            List<Platz> result = auffuehrungDao.findCheapest(d.getInput().get(0));
            Assert.assertTrue(resultIsOneOf(result, expected));
        }
    }

    /*
     * 0626629 Dominik Hofer
     */
    @Test
    public void findCheapestCountSomeTest() {
        loadData("dh_dataset.xml");

        List<Data> data = loadTestfile("dh_findCheapestCountSome");

        for (Data d : data) {
            List<List<Integer>> expected = d.getOutput();
            List<Platz> result = auffuehrungDao.findBest(d.getInput().get(0), d.getInput().get(1));
            Assert.assertTrue(resultIsOneOf(result, expected));
        }
    }

    /*
     * 0726284 Richard Holzeis
     */
    @Test
    @Transactional
    public void rh_findBest1Test() {

        this.loadData("rh_dataset.xml");
        List<Data> dataList = this.loadTestfile("rh_testdata");

        List<Platz> list = auffuehrungDao.findBest(1);

        for (Data data : dataList) {
            Assert.assertTrue(resultIsOneOf(list, data.getOutput()));
        }

    }

    /*
     * 0726284 Richard Holzeis
     */
    @Test
    @Transactional
    public void rh_findBest1CountTest() {

        this.loadData("rh_dataset.xml");
        List<Data> dataList = this.loadTestfile("rh_testdata");
        int count = 1;

        for (Data data : dataList) {
            List<Platz> list = auffuehrungDao.findBest(data.getInput().get(0), count);
            Assert.assertTrue(resultIsOneOf(list, data.getOutput()));
            Assert.assertEquals(list.size(), count);
        }
    }

    /*
     * 0726284 Richard Holzeis
     */
    @Test
    @Transactional
    public void rh_findBest1CountMaxPriceBest() {

        this.loadData("rh_dataset.xml");
        List<Data> dataList = this.loadTestfile("rh_testdata");
        int count = 1;

        for (Data data : dataList) {
            List<Platz> list = auffuehrungDao.findBest(data.getInput().get(0), count, new BigDecimal(40));
            Assert.assertTrue(resultIsOneOf(list, data.getOutput()));
            Assert.assertEquals(list.size(), count);
        }
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
