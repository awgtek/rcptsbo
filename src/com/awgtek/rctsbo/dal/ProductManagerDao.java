package com.awgtek.rctsbo.dal;

import java.util.List;

import com.awgtek.rctsbo.bll.Product;

public interface ProductManagerDao {

    public List getProductList();

    public void increasePrice(Product prod, int pct);
}
