package at.ticketline.dao.jpa;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;


import junit.framework.Assert;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
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
	private VeranstaltungDao veranstaltungDao;
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
        	
        	DatabaseOperation.CLEAN_INSERT.execute(dbUnitCon, dataSet);
        	DatabaseOperation.REFRESH.execute(dbUnitCon, dataSet);
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	@Test
	@Transactional
	public void simpleTest() {
		loadData("dataset.xml");
		
		Assert.assertEquals(4, 2 + 2);
		Assert.assertNotNull(this.veranstaltungDao);
		Assert.assertEquals(this.countRowsInTable("veranstaltung"), 3);
		
		Assert.assertEquals(this.veranstaltungDao.findAll().size(), 3);
		
		//List<Platz> bestplatz = this.auffuehrungDao.findBest(1);
		
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
