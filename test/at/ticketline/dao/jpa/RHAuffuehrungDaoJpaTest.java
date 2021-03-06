package at.ticketline.dao.jpa;

import java.math.BigDecimal;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import at.ticketline.dao.AbstractDaoTest;
import at.ticketline.entity.Platz;

public class RHAuffuehrungDaoJpaTest extends AbstractDaoTest  {


    /*
     * 0726284 Richard Holzeis
     */
    @Test
    @Transactional
    public void rh_findBest1Test() {

        this.loadData("rh_dataset.xml");
        List<Data> dataList = this.loadTestfile("rh_findBest1");
        boolean flag=true;

        for (Data data : dataList) {
            for (Integer in:data.getInput()) {
                List<Platz> list = auffuehrungDao.findBest(in);
                flag &= resultIsOneOf(list, data.getOutput());
            }
        }
        Assert.assertTrue(flag);
    }

    /*
     * 0726284 Richard Holzeis
     */
    @Test
    @Transactional
    public void rh_findBest1CountTest() {

        this.loadData("rh_dataset.xml");
        List<Data> dataList = this.loadTestfile("rh_findBest1Count");
        boolean flag=true;

        for (Data data : dataList) {
            List<Platz> list = auffuehrungDao.findBest(data.getInput().get(0), data.getInput().get(1));
            flag &= resultIsOneOf(list, data.getOutput());
        }
        Assert.assertTrue(flag);
    }

    /*
     * 0726284 Richard Holzeis
     */
    @Test
    @Transactional
    public void rh_findBest1CountMaxPriceBest() {

        this.loadData("rh_dataset.xml");
        List<Data> dataList = this.loadTestfile("rh_findBest1CountMaxPriceBest");
        boolean flag=true;

        for (Data data : dataList) {
            List<Platz> list = auffuehrungDao.findBest(data.getInput().get(0), data.getInput().get(1), new BigDecimal(data.getInput().get(2)));
            flag &= resultIsOneOf(list, data.getOutput());
        }
        Assert.assertTrue(flag);
    }
    
    /*
     * 0726284 Richard Holzeis
     */
    @Test
    @Transactional
    public void rh_findCheapest1() {

        this.loadData("rh_dataset.xml");
        List<Data> dataList = this.loadTestfile("rh_findCheapest1");
        boolean flag=true;

        for (Data data : dataList) {
            List<Platz> list = auffuehrungDao.findCheapest((data.getInput().get(0)));
            flag &= resultIsOneOf(list, data.getOutput());
        }
        Assert.assertTrue(flag);
    }
    
    /*
     * 0726284 Richard Holzeis
     */
    @Test
    @Transactional
    public void rh_findCheapest1CountTest() {

        this.loadData("rh_dataset.xml");
        List<Data> dataList = this.loadTestfile("rh_findCheapest1CountMaxPriceBest");
        boolean flag=true;

        for (Data data : dataList) {
            List<Platz> list = auffuehrungDao.findCheapest(data.getInput().get(0), data.getInput().get(1));
            flag &= resultIsOneOf(list, data.getOutput());
        }
        
        Assert.assertTrue(flag);
    }
    
}
