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

import com.awgtek.rcptsbo.domain.Store;
import com.awgtek.rctsbo.bll.Product;

public class StoreDaoImpl implements StoreDao {
	private static final Logger logger = LoggerFactory.getLogger(StoreDaoImpl.class);
	private static final String ALL_STORES_SQL = "SELECT id, storename, storeaddress FROM stores order by storename, storeaddress";
	private static final String STORE_SQL = "SELECT id, storename, storeaddress FROM stores where id = ?";
	private DataSource ds;

	@Override
	public long save(Store store) {
		logger.info("inserting store: " + store);
		SqlUpdate su = new SqlUpdate(ds, 
				"insert into stores (storename, storeaddress) values(?, ?)");
		su.declareParameter(new SqlParameter(Types.VARCHAR));
		su.declareParameter(new SqlParameter(Types.VARCHAR));
		Object[] oa = new Object[2];
		oa[0] = store.getName();
		oa[1] = store.getAddress();
		int rowsAffected = su.update(oa);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		long idOfInsert = jdbcTemplate.queryForLong("SELECT limit 0 1 id from stores order by id desc");
		logger.info("rows affected: " + rowsAffected);
		return idOfInsert;
	}
	
	@Override
	public List<Store> getStores() {
		logger.info("getting stores");
		StoresQuery rq = new StoresQuery(ds, ALL_STORES_SQL);
		return rq.execute();
	}

	@Override
	public Store getStore(long storeId) {
		StoresQuery sq = new StoresQuery(ds, STORE_SQL, new SqlParameter("id", Types.INTEGER));
		return (Store) sq.findObject(storeId);
	}

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	class StoresQuery extends MappingSqlQuery {

		public StoresQuery(DataSource ds, String sql) {
			super(ds, sql);
			compile();
		}

		public StoresQuery(DataSource ds, String sql, SqlParameter sp) {
			super(ds, sql);
			super.declareParameter(sp);
			compile();
		}

		@Override
		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Store store = new Store();
			store.setId(rs.getInt("id"));
			store.setName(rs.getString("storename"));
			store.setAddress(rs.getString("storeaddress"));
			return store;
		}
		
	}

}
