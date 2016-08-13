package com.awgtek.rcptsbo.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.awgtek.rctsbo.bll.PriceIncrease;
import com.awgtek.rctsbo.bll.ProductManager;

public class PriceIncreaseFormController extends SimpleFormController {
	private final static Logger logger = LoggerFactory.getLogger(PriceIncreaseFormController.class);
	
	private ProductManager prodMan;
	
	public ModelAndView onSubmit(Object command) 
			throws ServletException {
		int increase = ((PriceIncrease) command).getPercentage();
		logger.info("Increasing prices by {}%.", increase);
		
		prodMan.increasePrice(increase);
		
		logger.info("returning from PriceIncreaseForm view to " + getSuccessView());
		
		return new ModelAndView(new RedirectView(getSuccessView()));
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		
		PriceIncrease priceIncrease = new PriceIncrease();
		priceIncrease.setPercentage(20);
		
		return priceIncrease;
	}

    public void setProductManager(ProductManager pm) {
        prodMan = pm;
    }

    public ProductManager getProductManager() {
        return prodMan;
    }
}
