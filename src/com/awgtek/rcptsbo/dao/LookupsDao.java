package com.awgtek.rcptsbo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.awgtek.rcptsbo.dao.ReceiptsDaoImpl.ReceiptsQuery;
import com.awgtek.rcptsbo.data.LookupTables;
import com.awgtek.rcptsbo.domain.Receipt;
import com.vaannila.LookupData;

public class LookupsDao {
	private static final Logger logger = LoggerFactory.getLogger(LookupsDao.class);
	DataSource ds;
	
	public LookupsDao(DataSource ds) {
		super();
		this.ds = ds;
	}

	public List<LookupData> getUniqueItems(String tableName) {
		tableName = LookupTables.valueOf(tableName).name();
		logger.info("getting receipts");
		ItemsQuery rq = new ItemsQuery(tableName);
		return rq.execute();
		
	}
	
	class ItemsQuery extends MappingSqlQuery {

		static final String format = "select distinct %s from receipt_items order by %s";
		private String tableName;
		
		public ItemsQuery(String tableName) {
			super(ds, String.format(format, tableName, tableName));
			this.tableName = tableName;
			compile();
		}

		@Override
		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			LookupData receipt = new LookupData(rs.getString(tableName));
			return receipt;
		}
		
	}

}
