package com.awgtek.rcptsbo.controller;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.awgtek.rcptsbo.dao.ReceiptItemsDaoImpl;
import com.awgtek.rcptsbo.domain.Receipt;
import com.awgtek.rcptsbo.domain.Store;
import com.awgtek.rcptsbo.service.ReceiptsService;
import com.awgtek.rcptsbo.service.StoreService;

public class ReceiptFormController extends SimpleFormController {
	private static final Logger logger = LoggerFactory.getLogger(ReceiptFormController.class);

	public static final String CURRENT_RECEIPT_ID = "currentReceiptId";
	private ReceiptsService receiptsService;
	private StoreService storeService;
	
    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true);
        binder.registerCustomEditor(Date.class, editor);
        binder.registerCustomEditor(Store.class, new PropertyEditorSupport() {
        	@Override
			public void setAsText(String text) {
				Store store = storeService.getStore(Long.valueOf(text));
				setValue(store);
			}
			});
    }
    
	@Override
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, 
			Object command, BindException errors) throws Exception {
		
		Receipt receipt = (Receipt) command;
		long receiptId = receiptsService.save(receipt);
		HttpSession session = request.getSession();
		logger.debug("found session " + session);
		session.setAttribute(CURRENT_RECEIPT_ID, receiptId);
		logger.debug("setting session attribute: " + receiptId);
		return new ModelAndView(new RedirectView(getSuccessView()));
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		
		Receipt receipt = new Receipt();
		Date date = new Date();
		receipt.setDateOfPurchase(date);
		
		return receipt;
	}
	
	@Override 
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map referenceData = new HashMap();
		referenceData.put("storeList", storeService.getStoreDictionary());
		return referenceData;
	}
	
	public void setReceiptsService(ReceiptsService receiptsService) {
		this.receiptsService = receiptsService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

}
