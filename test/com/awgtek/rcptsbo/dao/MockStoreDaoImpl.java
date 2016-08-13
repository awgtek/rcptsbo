package com.awgtek.rcptsbo.dao;

import java.util.ArrayList;
import java.util.List;

import com.awgtek.rcptsbo.domain.Store;

public class MockStoreDaoImpl implements StoreDao {

	private List<Store> savedStores = new ArrayList<Store>();
	
	@Override
	public long save(Store store) {
		System.out.println("saving " + store);
		savedStores.add(store);
		return 123;
	}

	@Override
	public List<Store> getStores() {
		return savedStores;
	}

	@Override
	public Store getStore(long storeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
