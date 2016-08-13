package com.awgtek.rcptsbo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.awgtek.rcptsbo.dao.ReceiptItemsDao;
import com.awgtek.rcptsbo.domain.Receipt;
import com.awgtek.rcptsbo.domain.ReceiptItem;

public class ReceiptItemsService {
	private static final Logger logger = LoggerFactory.getLogger(ReceiptItemsService.class);


	ReceiptItemsDao receiptItemsDao;

	
	public void setReceiptItemsDao(ReceiptItemsDao receiptItemsDao) {
		this.receiptItemsDao = receiptItemsDao;
	}
	

	public void save(ReceiptItem receiptItem) {
		long idOfInsert = receiptItemsDao.save(receiptItem);
		logger.info("idOfInsert: " + idOfInsert);
	}
	
	public List<ReceiptItem> getLastFewReceiptItems() {
		return receiptItemsDao.getLastFewReceiptItems();
	}
}
