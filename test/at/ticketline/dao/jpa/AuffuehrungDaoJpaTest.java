package at.ticketline.dao.jpa;

import java.math.BigDecimal;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import at.ticketline.dao.AbstractDaoTest;
import at.ticketline.entity.Platz;

public class AuffuehrungDaoJpaTest extends AbstractDaoTest {

    @BeforeTransaction
    public void beforeTransaction() {
        System.out.println("Before Transaction");
    }

    @AfterTransaction
    public void afterTransaction() {
        System.out.println("After Transaction");
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

}
