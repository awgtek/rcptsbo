package com.awgtek.rcptsbo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.core.JdbcTemplate;

import com.awgtek.rcptsbo.domain.Receipt;
import com.awgtek.rctsbo.bll.Product;

public class ReceiptsDaoImpl implements ReceiptsDao {
	private static final Logger logger = LoggerFactory.getLogger(ReceiptsDaoImpl.class);
	
	private StoreDao storeDao;
	private DataSource ds;

	@Override
	public long save(Receipt receipt) {
		logger.info("inserting receipt: " + receipt);
		SqlUpdate su = new SqlUpdate(ds, 
				"insert into receipts (storeid, dateofpurchase) values(?, ?)");
		su.declareParameter(new SqlParameter(Types.BIGINT));
		su.declareParameter(new SqlParameter(Types.DATE));
		Object[] oa = new Object[2];
		oa[0] = receipt.getStore().getId();
		oa[1] = receipt.getDateOfPurchase();
		int rowsAffected = su.update(oa);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		long idOfInsert = jdbcTemplate.queryForLong("SELECT limit 0 1 id from receipts order by id desc");
		logger.info("rows affected: " + rowsAffected);
		return idOfInsert;
	}
	
	@Override
	public List<Receipt> getLastFewReceipts() {
		logger.info("getting receipts");
		ReceiptsQuery rq = new ReceiptsQuery(ds);
		return rq.execute();
	}

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public StoreDao getStoreDao() {
		return storeDao;
	}

	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	class ReceiptsQuery extends MappingSqlQuery {

		public ReceiptsQuery(DataSource ds) {
			super(ds, "SELECT limit 0 5 id, storeid, dateofpurchase FROM receipts order by dateofpurchase desc");
			compile();
		}

		@Override
		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Receipt receipt = new Receipt();
			receipt.setId(rs.getInt("id"));
			receipt.setStore(storeDao.getStore(rs.getLong("storeid")));
			receipt.setDateOfPurchase(rs.getDate("dateofpurchase"));
			return receipt;
		}
		
	}

}
