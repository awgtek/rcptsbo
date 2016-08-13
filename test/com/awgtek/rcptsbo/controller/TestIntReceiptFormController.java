package com.awgtek.rcptsbo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;

import com.awgtek.rcptsbo.domain.Receipt;
import com.awgtek.rcptsbo.domain.Store;

import junit.framework.TestCase;

public class TestIntReceiptFormController extends TestCase {
	private ApplicationContext ac;

	public void setUp() {
		ac = new FileSystemXmlApplicationContext(
				"test/WEB-INF/test-int-springapp-servlet.xml");
	}
	
	public void testOnSubmit() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		
		Store store = new Store();
		store.setName("Carrefour");;
		store.setAddress("123 Main");
		Long storeId = insertStore(store);
		if (storeId == null ) {
			storeId = 0L;
		}
		store.setId(storeId);
				
		
		Receipt receipt = new Receipt();
		Date today = new Date();
		receipt.setDateOfPurchase(today);
		receipt.setStore(store);
		
		ReceiptFormController rfc = (ReceiptFormController) ac.getBean("receiptFormController");
		rfc.onSubmit(request, null, receipt, null);
		Receipt addedReceipt = queryAddedReceipt();
		Calendar calendarExpected = new GregorianCalendar();
		calendarExpected.setTime(today);
		Calendar calendarActual = new GregorianCalendar();
		calendarActual.setTime(addedReceipt.getDateOfPurchase());
		
		assertEquals("month", calendarExpected.get(Calendar.MONTH), 
				calendarActual.get(Calendar.MONTH));
		assertEquals("day", calendarExpected.get(Calendar.DATE), 
				calendarActual.get(Calendar.DATE));
		assertEquals(receipt.getStore(), addedReceipt.getStore());
	}

	private Long insertStore(Store store) throws SQLException {
		Connection dbConnection = getDBConnection();
		String sql = "insert into stores (storename, storeaddress) values (?, ?)";
		//PreparedStatement ps = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
		PreparedStatement ps = dbConnection.prepareStatement(sql);
		ps.setString(1, store.getName());
		ps.setString(2, store.getAddress());
		int updateCount = ps.executeUpdate();
		//these "generatedkeys" not supported by this version of hsqldb
//		ResultSet rs = ps.getGeneratedKeys();
//		while (rs.next()) {
//			Long idColVar = rs.getLong(1);
//			return idColVar;
//		}
		return null;
	}

	private Receipt queryAddedReceipt() throws Exception {
		Connection dbConnection = getDBConnection();
		Statement statement = dbConnection.createStatement();
		String sql = "SELECT id, storeid, dateofpurchase from receipts";
		System.out.println(sql);
		ResultSet rs = statement.executeQuery(sql);
		Receipt receipt = new Receipt();
		while (rs.next()) {
			long storeId = rs.getLong("storeid");
			Store store = queryForStore(dbConnection, storeId);
			Date dateOfPurchase = rs.getDate("dateofpurchase");
			receipt.setId(rs.getLong("id"));
			receipt.setStore(store);
			receipt.setDateOfPurchase(dateOfPurchase);
		}
		return receipt;
	}

	private Store queryForStore(Connection dbConnection, long storeId) throws SQLException {
		String sql = "select id, storename, storeaddress from stores where id = ?";
		PreparedStatement ps = dbConnection.prepareStatement(sql);
		ps.setLong(1, storeId);
		ResultSet rs = ps.executeQuery();
		rs.next();
		Store store = new Store();
		store.setId(rs.getLong("id"));
		store.setName(rs.getString("storename"));
		store.setAddress(rs.getString("storeaddress"));
		return store;
	}

	private Connection getDBConnection() {
		Connection dbConnection = null;
		Properties properties = new Properties();
		InputStream input = null;
		
		String dbPropertiesFile = "db-JUNIT.properties";
		input = TestIntReceiptFormController.class.getClassLoader().getResourceAsStream(dbPropertiesFile);
		try {
			properties.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("db.url", 
				"jdbc:hsqldb:C:/Work/workspaces/rcpts/rcptsbo/db/antjunit");
		String user = properties.getProperty("db.user", "sa");
		String password = properties.getProperty("db.password", "");
		String driverClassName = properties.getProperty("db.driverClassName", "org.hsqldb.jdbcDriver");
		
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dbConnection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dbConnection;
	}
}