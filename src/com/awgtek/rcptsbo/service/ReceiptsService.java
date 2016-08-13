package com.awgtek.rcptsbo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.awgtek.rcptsbo.dao.ReceiptsDao;
import com.awgtek.rcptsbo.domain.Receipt;

public class ReceiptsService {
	private static final Logger logger = LoggerFactory.getLogger(ReceiptsService.class);

	ReceiptsDao receiptsDao;
	
	public ReceiptsDao getReceiptsDao() {
		return receiptsDao;
	}

	public void setReceiptsDao(ReceiptsDao receiptsDao) {
		this.receiptsDao = receiptsDao;
	}

	public long save(Receipt receipt) {
		long idOfInsert = receiptsDao.save(receipt);
		logger.info("idOfInsert: " + idOfInsert);
		return idOfInsert;
	}
	
	public List<Receipt> getLastFewReceipts() {
		return receiptsDao.getLastFewReceipts();
	}

}
