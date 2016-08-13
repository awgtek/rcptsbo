package com.awgtek.rcptsbo.domain;

import java.util.Date;

public class Receipt {

	private long id;
	private Store store;
	private Date dateOfPurchase;
	private ReceiptItem[] receiptItems;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public ReceiptItem[] getReceiptItems() {
		return receiptItems;
	}
	public void setReceiptItems(ReceiptItem[] receiptItems) {
		this.receiptItems = receiptItems;
	}
	
	public String getLabel() {
		return store.getAbbreviation() + " | " + dateOfPurchase;
	}
	
	@Override
	public String toString() {
		return "Receipt [id=" + id + ", store=" + store + ", dateOfPurchase=" + dateOfPurchase + "]";
	}
	
}
