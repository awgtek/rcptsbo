package com.awgtek.rcptsbo.controller;

import org.springframework.web.servlet.mvc.Controller;

import com.awgtek.rcptsbo.service.ReceiptItemsService;
import com.awgtek.rcptsbo.service.ReceiptsService;
import com.awgtek.rcptsbo.service.StoreService;
import com.awgtek.rctsbo.bll.ProductManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpringappController implements Controller {
	private final static Logger logger = LoggerFactory.getLogger(SpringappController.class);

    private ProductManager prodMan;
	private ReceiptItemsService receiptItemsService;
	private ReceiptsService receiptsService;
	private StoreService storeService;
   
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	logger.debug("[welcomenn]");
    	logger.info("SpringappController - returning hello view");

    	String now = (new java.util.Date()).toString();
    	logger.info("returning hello view with {}", now);

        Map myModel = new HashMap();
        myModel.put("now", now);
        myModel.put("products", getProductManager().getProducts());
        myModel.put("receipts", receiptsService.getLastFewReceipts());
        myModel.put("receiptItems", receiptItemsService.getLastFewReceiptItems());
        myModel.put("storeList", storeService.getStoreDictionary());


        return new ModelAndView("hello", "model", myModel);
    }

    public void setProductManager(ProductManager pm) {
        prodMan = pm;
    }

    public ProductManager getProductManager() {
        return prodMan;
    }

	public void setReceiptItemsService(ReceiptItemsService receiptItemsService) {
		this.receiptItemsService = receiptItemsService;
	}

	public void setReceiptsService(ReceiptsService receiptsService) {
		this.receiptsService = receiptsService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
    
}