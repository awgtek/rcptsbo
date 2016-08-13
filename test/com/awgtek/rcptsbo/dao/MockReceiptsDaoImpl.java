package com.awgtek.rcptsbo.dao;

import java.util.List;

import com.awgtek.rcptsbo.domain.Receipt;

public class MockReceiptsDaoImpl implements ReceiptsDao {

	@Override
	public long save(Receipt receipt) {
		System.out.println("saving " + receipt);
		return 0;
	}

	@Override
	public List<Receipt> getLastFewReceipts() {
		// TODO Auto-generated method stub
		return null;
	}

}
