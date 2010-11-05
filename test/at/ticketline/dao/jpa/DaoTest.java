package at.ticketline.dao.jpa;

import java.io.FileReader;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;


import junit.framework.Assert;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import at.ticketline.dao.AbstractDaoTest;
import at.ticketline.dao.interfaces.ArtikelDao;
import at.ticketline.dao.interfaces.AuffuehrungDao;
import at.ticketline.dao.interfaces.VeranstaltungDao;
import at.ticketline.entity.Auffuehrung;
import at.ticketline.entity.News;
import at.ticketline.entity.Platz;
import at.ticketline.entity.Veranstaltung;

public class DaoTest extends AbstractDaoTest {

	
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
	public void loadData() {
        Connection con = this.getSqlConnection();
        
        try {       
        	IDatabaseConnection dbUnitCon = this.getDbUnitConnection();
        //IDataSet dataSet = new FlatXmlDataSet(new FileInputStream("./src/test/resources/dbunit-test-data/MyEntityDaoImpl.xml"));
        	IDataSet dataSet = new XmlDataSet(new FileReader("dataset.xml")); 
        	
           // DatabaseOperation.CLEAN_INSERT.execute(this.getDbUnitConnection(), dataSet);
            //REFRESH.execute(dbUnitCon, dataSet);
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
           // DataSourceUtils.releaseConnection(con, dataSource);
        }

	}
	
	@Test
	public void simpleTest() {
		Assert.assertEquals(4, 2 + 2);
		Assert.assertNotNull(this.veranstaltungDao);
		Assert.assertEquals(this.countRowsInTable("veranstaltung"), 3);
		
		Assert.assertEquals(this.veranstaltungDao.findAll().size(), 3);
		
		//Example how to call findBest(ID) method
		//List<Platz> bestplatz = this.auffuehrungDao.findBest(1);
		
	}
	
}
