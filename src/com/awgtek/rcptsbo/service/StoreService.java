package com.awgtek.rcptsbo.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.awgtek.rcptsbo.dao.StoreDao;
import com.awgtek.rcptsbo.domain.Store;

public class StoreService {

	private StoreDao storeDao;
	
	public Store getStore(long id) {
		return storeDao.getStore(id);
	}
	
	public Map<Long, String> getStoreDictionary() {
		List<Store> storeList = storeDao.getStores();
		
		Map<Long,String> stores = new LinkedHashMap<Long,String>();
		for (Store store: storeList) {
			stores.put(store.getId(), store.getAbbreviation());
		}
//		stores.put("WmtNMatSCSpfd", "Walmart Neighborhood Market at S. Cambell Spfd, MO");
//		stores.put("PCatSNSpfd", "Price Cutter at S. National Spfd, MO");
		return stores;
	}

	public void save(Store store) {
		storeDao.save(store);
		
	}

	public StoreDao getStoreDao() {
		return storeDao;
	}

	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}
}
