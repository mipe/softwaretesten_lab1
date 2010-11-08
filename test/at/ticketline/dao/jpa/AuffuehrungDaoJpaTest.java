package at.ticketline.dao.jpa;

import java.io.FileReader;
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
	public void findBest2Best() {
		loadData("dh_findBest2Best1RowTest.xml");
		
		int[] ids = new int[] {1,2}; 
		
		List<Platz> result = auffuehrungDao.findBest(1);

		Assert.assertTrue(resultContainsOneOf(result, ids));
	}
	
	/*
	 * 0626629
	 * Dominik Hofer
	 */
	@Test
	public void findBest1BestCount() {
		loadData("dh_findBest2Best1RowTest.xml");

		int[] ids1 = new int[] {1,2};
		int[] ids2 = new int[] {2,3};
		
		List<Platz> result = auffuehrungDao.findBest(1,2);

		Assert.assertTrue(resultContainsExactly(result, ids1) || resultContainsExactly(result, ids2));
	}

	/*
	 * 0626629
	 * Dominik Hofer
	 */
	@Test
	public void findBest1CountMaxPriceBest() {
		loadData("dh_findBest2Best1RowTest.xml");
		
		int[] ids1 = new int[] {1,2};
		int[] ids2 = new int[] {2,3};
		
		List<Platz> result = auffuehrungDao.findBest(1,2,new BigDecimal(150));

		Assert.assertTrue(resultContainsExactly(result, ids1) || resultContainsExactly(result, ids2));
	}
	
	/*
	 * 0626629
	 * Dominik Hofer
	 */
	@Test
	public void findCheapest2Cheapest() {
		loadData("dh_findBest2Best1RowTest.xml");
		
		int[] ids1 = new int[] {4,5,6};
		
		List<Platz> result = auffuehrungDao.findCheapest(1);

		Assert.assertTrue(resultContainsExactly(result, ids1));
	}

	/*
	 * 0626629
	 * Dominik Hofer
	 */
	@Test
	public void findCheapest2CheapestCount() {
		loadData("dh_findBest2Best1RowTest.xml");
		
		int[] ids1 = new int[] {4,5};
		int[] ids2 = new int[] {5,6};
		
		List<Platz> result = auffuehrungDao.findCheapest(1,2);
		System.out.println(result);
		Assert.assertTrue(resultContainsExactly(result, ids1) || resultContainsExactly(result, ids2));
	}
	
	private boolean resultContainsExactly(List<Platz> list, int[] ids) {
		if (list.size() != ids.length) return false;
		
		ids : for (Integer id : ids) {
			boolean contains = false;
			for (Platz p : list)
				if (p.getId().equals(id)) {
					contains = true;
					continue ids;
				}
			if (!contains) return false;
		}
		
		return true;
	}
	
	private boolean resultContainsOneOf(List<Platz> list, int[] ids) {
		for (Integer id : ids) {
			for (Platz p : list)
				if (p.getId().equals(id)) {
					return true;
				}
		}
		
		return false;
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
