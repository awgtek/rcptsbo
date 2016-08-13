package com.awgtek.rcptsbo.dao;

import java.util.List;

import com.awgtek.rcptsbo.domain.Receipt;

public interface ReceiptsDao {

	long save(Receipt receipt);
	List<Receipt> getLastFewReceipts();
}
