package com.awgtek.rcptsbo.dao;

import java.util.List;

import com.awgtek.rcptsbo.domain.ReceiptItem;

public interface ReceiptItemsDao {

	long save(ReceiptItem receiptItem);
	List<ReceiptItem> getLastFewReceiptItems();
}
