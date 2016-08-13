package com.awgtek.rctsbo.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import com.awgtek.rctsbo.bll.Product;

public class ProductManagerDaoJdbc implements ProductManagerDao {
	private static final Logger logger = LoggerFactory.getLogger(ProductManagerDaoJdbc.class);
	
	private DataSource ds;
	
	@Override
	public List getProductList() {
		logger.info("getting produts");
		ProductQuery pq = new ProductQuery(ds);
		return pq.execute();
	}

	@Override
	public void increasePrice(Product prod, int pct) {
		logger.info("Increasing price by " + pct + "%");
		SqlUpdate su = 
				new SqlUpdate(ds, "update products set price = price * (100 + ?) / 100 where id = ?");
		su.declareParameter(new SqlParameter("increase", Types.INTEGER));
		su.declareParameter(new SqlParameter("ID", Types.INTEGER));
		su.compile();
		Object[] oa = new Object[2];
		oa[0] = new Integer(pct);
		oa[1] = new Integer(prod.getId());
		int count = su.update(oa);
		logger.info("Rows affected: {}", count);
	}
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	class ProductQuery extends MappingSqlQuery {

		public ProductQuery(DataSource ds) {
			super(ds, "SELECT id, description, price from products");
			compile();
		}

		@Override
		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product prod = new Product();
			prod.setId(rs.getInt("id"));
			prod.setDescription(rs.getString("description"));
			prod.setPrice(new Double(rs.getDouble("price")));
			return prod;
		}
		
	}
}
