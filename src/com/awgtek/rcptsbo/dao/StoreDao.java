package com.awgtek.rcptsbo.dao;

import java.util.List;

import com.awgtek.rcptsbo.domain.Store;

public interface StoreDao {

	long save(Store store);
	Store getStore(long storeId);
	List<Store> getStores();
}
