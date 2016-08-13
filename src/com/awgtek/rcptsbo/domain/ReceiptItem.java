package com.awgtek.rcptsbo.domain;

public class ReceiptItem {
	
	long id;
	String productName;
	String productBrand;
	String genericName;
	Integer quantity;
	double totalCost;
	Double sizeUnitAmount;
	String sizeUnitType;
	long sizeUnitCount;
	long receiptId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	public String getGenericName() {
		return genericName;
	}
	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public Double getSizeUnitAmount() {
		return sizeUnitAmount;
	}
	public void setSizeUnitAmount(Double sizeUnitAmount) {
		this.sizeUnitAmount = sizeUnitAmount;
	}
	public String getSizeUnitType() {
		return sizeUnitType;
	}
	public void setSizeUnitType(String sizeUnitType) {
		this.sizeUnitType = sizeUnitType;
	}
	public long getSizeUnitCount() {
		return sizeUnitCount;
	}
	public void setSizeUnitCount(long sizeUnitCount) {
		this.sizeUnitCount = sizeUnitCount;
	}
	public long getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(long receiptId) {
		this.receiptId = receiptId;
	}
	@Override
	public String toString() {
		return "ReceiptItem [id=" + id + ", productName=" + productName + ", productBrand=" + productBrand
				+ ", genericName=" + genericName + ", quantity=" + quantity + ", totalCost=" + totalCost
				+ ", sizeUnitAmount=" + sizeUnitAmount + ", sizeUnitType=" + sizeUnitType + ", receiptId=" + receiptId
				+ "]";
	}
	
	
}
