package com.awgtek.rcptsbo.init;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

public class DbUpgrader {
	private static final Logger logger = LoggerFactory.getLogger(DbUpgrader.class);
	
	private DataSource ds;
	
	public void execute() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		String sql = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.SYSTEM_COLUMNS"
				 + " WHERE TABLE_NAME = 'RECEIPT_ITEMS' AND COLUMN_NAME = ?";
		Object[] args = new Object[] {"SIZEUNITCOUNT"};
		int colCount = jdbcTemplate.queryForInt(sql , args );
		if (colCount == 0) {
			logger.debug("Adding sizeunitcount column");
			String insertSql = "ALTER TABLE RECEIPT_ITEMS ADD sizeunitcount BIGINT BEFORE receiptid";
			jdbcTemplate.execute(insertSql);
			colCount = jdbcTemplate.queryForInt(sql , args );
			if (colCount == 0) {
				throw new RuntimeException("Failed to add sizeunitcount column!");
			} else {
				logger.debug("added sizeunitcount column");
			}
			
		} else {
			logger.debug("sizeunitcount column already exists");
		//	jdbcTemplate.execute("alter table receipt_items drop sizeunitcount");
		}
	}

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

}
