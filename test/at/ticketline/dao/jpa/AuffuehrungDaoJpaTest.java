package at.ticketline.dao.jpa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;


import junit.framework.Assert;

import org.apache.commons.collections.CollectionUtils;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.type.BigDecimalType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import at.ticketline.dao.AbstractDaoTest;
import at.ticketline.dao.interfaces.ArtikelDao;
import at.ticketline.dao.interfaces.AuffuehrungDao;
import at.ticketline.dao.interfaces.VeranstaltungDao;
import at.ticketline.entity.Auffuehrung;
import at.ticketline.entity.News;
import at.ticketline.entity.Platz;
import at.ticketline.entity.Veranstaltung;

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
        	//DatabaseOperation.INSERT.execute(dbUnitCon, dataSet);
        	DatabaseOperation.CLEAN_INSERT.execute(dbUnitCon, dataSet);
        	DatabaseOperation.REFRESH.execute(dbUnitCon, dataSet);
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	/*
	 * 0626629
	 * Dominik Hofer
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
	 * 0626629
	 * Dominik Hofer
	 */
	@Test
	public void dhFindBestCountSomeTest() {
		loadData("dh_dataset.xml");
		
		List<Data> data = loadTestfile("dh_findBestCountSome");
		
		for (Data d : data) {
			List<List<Integer>> expected = d.getOutput();
			List<Platz> result = auffuehrungDao.findBest(d.getInput().get(0),d.getInput().get(1));
			Assert.assertTrue(resultIsOneOf(result, expected));
		}
	}

	/*
	 * 0626629
	 * Dominik Hofer
	 */
	@Test
	public void dhFindBestCountMaxPriceSomeTest() {
		loadData("dh_dataset.xml");
		
		List<Data> data = loadTestfile("dh_findBestCountMaxPriceSome");
		
		for (Data d : data) {
			List<List<Integer>> expected = d.getOutput();
			List<Platz> result = auffuehrungDao.findBest(d.getInput().get(0),d.getInput().get(1),new BigDecimal(d.getInput().get(2)));
			Assert.assertTrue(resultIsOneOf(result, expected));
		}
	}

	/*
	 * 0626629
	 * Dominik Hofer
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
	 * 0626629
	 * Dominik Hofer
	 */
	@Test
	public void findCheapestCountSomeTest() {
		loadData("dh_dataset.xml");
		
		List<Data> data = loadTestfile("dh_findCheapestCountSome");
		
		for (Data d : data) {
			List<List<Integer>> expected = d.getOutput();
			List<Platz> result = auffuehrungDao.findBest(d.getInput().get(0),d.getInput().get(1));
			Assert.assertTrue(resultIsOneOf(result, expected));
		}
	}
	
	/*
	 * 0726284
	 * Richard Holzeis
	*/
	@Test
    @Transactional
    public void findBest1Test() {
        
        this.loadData("rh_dataset.xml");
        
        int expectedId = 1;
        List<Platz> list = auffuehrungDao.findBest(1);
        
        //Assert.assertTrue(resultContainsExactly(list, new int[] {expectedId}));
    }

    /*
     * 0726284
     * Richard Holzeis
    */
    @Test
    @Transactional
    public void findBest1CountTest() {
        
        this.loadData("rh_dataset.xml");
        
        int expectedId = 1;
        int count = 1;
        
        List<Platz> list = auffuehrungDao.findBest(2,count);
        
//        Assert.assertTrue(resultContainsExactly(list, new int[] {expectedId}));
//        Assert.assertEquals(list.size(), count);
    }    
	
	private boolean resultIsOneOf(List<Platz> result, List<List<Integer>> expected) {
		ex : for (List<Integer> ex : expected) {
			if (result.size() != ex.size()) continue;
			ids : for (Integer id : ex) {
				for (Platz p : result)
					if (p.getId().equals(id)) continue ids;
				continue ex;
			}	
			return true;
		}
		return false;
	}
	
	private boolean resultContainsOneOf(List<Platz> list, List<Integer> ids) {
		for (Integer id : ids) {
			for (Platz p : list)
				if (p.getId().equals(id)) {
					return true;
				}
		}
		
		return false;
	}
	
	private List<Data> loadTestfile(String testfile) {
	    List<Data> data = new ArrayList<Data>();
	    try {
            BufferedReader reader = new BufferedReader(new FileReader(testfile));
            String input = "";
            while ((input = reader.readLine()) != null && !input.equals("") ) {
                String[] split = input.split(":");
                List<Integer> inputList = new ArrayList<Integer>();
                List<List<Integer>> outputList = new ArrayList<List<Integer>>();
                
                //[0]: input
                String[] eingabe = split[0].split(",");
                for (String in:eingabe) {
                    inputList.add(new Integer(in));
                }
                
                //[>0]: output
                for (int i=1; i<split.length; i++) {
                    String[] ausgabe = split[i].split(",");
                    List<Integer> list = new ArrayList<Integer>();
                    for (String out:ausgabe) {
                        list.add(new Integer(out));
                    }
                    outputList.add(list);
                }
                
                data.add(new Data(inputList,outputList));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
	    return data;
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
