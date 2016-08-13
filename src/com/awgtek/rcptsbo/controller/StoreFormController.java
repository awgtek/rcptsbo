package com.awgtek.rcptsbo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.awgtek.rcptsbo.domain.Store;
import com.awgtek.rcptsbo.service.StoreService;

public class StoreFormController extends SimpleFormController {
	private static final Logger logger = LoggerFactory.getLogger(StoreFormController.class);

	private StoreService storeService;

	@Override
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, 
			Object command, BindException errors) {
		Store store = (Store) command;
		logger.debug("saving {} errors {}", store, 
				(errors == null) ? null : errors.getAllErrors());
		storeService.save(store);
		return new ModelAndView(new RedirectView(getSuccessView()));
		
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) {
		Store store = new Store();
		return store;
	}

	public StoreService getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

}
