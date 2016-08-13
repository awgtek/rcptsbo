package com.awgtek.rcptsbo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import com.awgtek.rcptsbo.domain.Receipt;
import com.awgtek.rcptsbo.domain.ReceiptItem;

public class ReceiptItemsDaoImpl implements ReceiptItemsDao {
	private static final Logger logger = LoggerFactory.getLogger(ReceiptItemsDaoImpl.class);
	
	private DataSource ds;

	@Override
	public long save(ReceiptItem receiptItem) {
/*    	CREATE TABLE receipt_items (
        	id BIGINT IDENTITY PRIMARY KEY,
        	productname varchar(255),
        	productbrand varchar(255),
        	genericname varchar(255),
        	quantity INTEGER,
        	totalcost DECIMAL(15,2) NOT NULL,
        	sizeunitamount DOUBLE,
        	sizeunittype varchar(255),
        	sizeunitcount BIGINT,
        	receiptid BIGINT,
        	FOREIGN KEY (receiptid) REFERENCES receipts
        );
*/		logger.info("inserting receiptItem: " + receiptItem);
		SqlUpdate su = new SqlUpdate(ds, 
				"insert into receipt_items "
				+ "(productname, productbrand, genericname, quantity, totalcost, "
				+ "sizeunitamount, sizeunittype, sizeunitcount, receiptid) "
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
		su.declareParameter(new SqlParameter("productname", Types.VARCHAR));
		su.declareParameter(new SqlParameter("productbrand", Types.VARCHAR));
		su.declareParameter(new SqlParameter("genericname", Types.VARCHAR));
		su.declareParameter(new SqlParameter("quantity", Types.INTEGER));
		su.declareParameter(new SqlParameter("totalcost", Types.DOUBLE));
		su.declareParameter(new SqlParameter("sizeunitamount", Types.DOUBLE));
		su.declareParameter(new SqlParameter("sizeunittype", Types.VARCHAR));
		su.declareParameter(new SqlParameter("sizeunitcount", Types.BIGINT));
		su.declareParameter(new SqlParameter("receiptid", Types.BIGINT));
		Object[] oa = new Object[9];
		oa[0] = receiptItem.getProductName();
		oa[1] = receiptItem.getProductBrand();
		oa[2] = receiptItem.getGenericName();
		oa[3] = receiptItem.getQuantity();
		oa[4] = receiptItem.getTotalCost();
		oa[5] = receiptItem.getSizeUnitAmount();
		oa[6] = receiptItem.getSizeUnitType();
		oa[7] = receiptItem.getSizeUnitCount();
		oa[8] = receiptItem.getReceiptId();
		int rowsAffected = su.update(oa);
		JdbcTemplate jdbcTemplate = su.getJdbcTemplate();
		long idOfInsert = jdbcTemplate.queryForLong("SELECT limit 0 1 id from receipt_items order by id desc");
		logger.info("rows affected: " + rowsAffected);
		return idOfInsert;
	}

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<ReceiptItem> getLastFewReceiptItems() {
		logger.info("getting receipt items");
		ReceiptItemsQuery rq = new ReceiptItemsQuery(ds);
		return rq.execute();
	}

	class ReceiptItemsQuery extends MappingSqlQuery {

		public ReceiptItemsQuery(DataSource ds) {
			super(ds, "SELECT limit 0 5 id, productname, productbrand, "
					+ "genericname, quantity, totalcost, sizeunitamount, "
					+ "sizeunittype, sizeunitcount, receiptid "
					+ "FROM receipt_items order by id desc;");
			compile();
		}

		@Override
		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReceiptItem receiptItem = new ReceiptItem();
			receiptItem.setId(rs.getInt("id"));
			receiptItem.setProductBrand(rs.getString("productbrand"));
			receiptItem.setGenericName(rs.getString("genericname"));
			receiptItem.setProductName(rs.getString("productname"));
			receiptItem.setQuantity(rs.getInt("quantity"));
			receiptItem.setSizeUnitCount(rs.getLong("sizeunitcount"));
			receiptItem.setReceiptId(rs.getLong("receiptid"));
			receiptItem.setSizeUnitAmount(rs.getDouble("sizeunitamount"));
			receiptItem.setSizeUnitType(rs.getString("sizeunittype"));
			receiptItem.setTotalCost(rs.getDouble("totalcost"));
			return receiptItem;
		}
		
	}

}
