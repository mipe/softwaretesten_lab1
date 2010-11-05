package at.ticketline.dao;

import java.sql.Connection;

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

/**
 * Abstrakte Testklasse fuer DAOs
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(transactionManager="txManager", defaultRollback=false)
public class AbstractDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Liefert die aktuelle SQL-Connection zurueck
	 * 
	 */
	protected Connection getSqlConnection() {
		DataSource dataSource = ((JdbcTemplate)this.simpleJdbcTemplate.getJdbcOperations()).getDataSource();
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
}
