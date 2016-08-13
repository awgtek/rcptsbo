package com.awgtek.rcptsbo.controller;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.awgtek.rcptsbo.data.SizeUnitType;
import com.awgtek.rcptsbo.domain.Receipt;
import com.awgtek.rcptsbo.domain.ReceiptItem;
import com.awgtek.rcptsbo.manage.ReceiptItemCounter;
import com.awgtek.rcptsbo.service.ReceiptItemsService;
import com.awgtek.rcptsbo.service.ReceiptsService;

public class ReceiptItemFormController extends SimpleFormController {
	private static final Logger logger = LoggerFactory.getLogger(ReceiptItemFormController.class);

	private ReceiptItemsService receiptItemsService;
	private ReceiptsService receiptsService;
	private ReceiptItemCounter receiptItemCounter;
		
    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        StringTrimmerEditor editor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, editor);
        CustomNumberEditor customNumberEditor
    	= new CustomNumberEditor(Double.class, 
    			NumberFormat.getCurrencyInstance(), true);
        binder.registerCustomEditor(Double.class, "totalCost", customNumberEditor);
        customNumberEditor
			= new CustomNumberEditor(Double.class, true);
		binder.registerCustomEditor(Double.class, "totalCost", customNumberEditor);
    }

    @Override
	public ModelAndView onSubmit(Object command) {
		
		ReceiptItem receiptItem = (ReceiptItem) command;
		receiptItemsService.save(receiptItem);
		logger.debug("setting jmx counter to {} and redirecting to {}", 
				receiptItemCounter.incrementAndGet(), getSuccessView());
		
		return new ModelAndView(new RedirectView(getSuccessView()));
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		
		ReceiptItem receiptItem = new ReceiptItem();
		receiptItem.setQuantity(new Integer(1));
		
		Long currentReceiptId = null;
		Long curRcptAttr = (Long) request.getSession()
				.getAttribute(ReceiptFormController.CURRENT_RECEIPT_ID);
		if (curRcptAttr != null) {
			logger.debug("found current receipt id {} in Session attribute", curRcptAttr);
			currentReceiptId = curRcptAttr;
		} else {
			Calendar yesterday = Calendar
					.getInstance();
			yesterday.add(Calendar.DAY_OF_YEAR, -1);
			Date dateYesterday = yesterday.getTime();
			List<Receipt> receipts = receiptsService.getLastFewReceipts();
			if (!receipts.isEmpty() && receipts.get(0).getDateOfPurchase().after(dateYesterday)) {
				currentReceiptId = receipts.get(0).getId();
				logger.debug("found receipt from {}", receipts.get(0).getDateOfPurchase());
			} else if (!receipts.isEmpty()) {
				currentReceiptId = receipts.get(0).getId();
			}
		}
		if (currentReceiptId != null) {
			receiptItem.setReceiptId(currentReceiptId);
		} else {
			logger.warn("did not find receipt");
		}
		return receiptItem;
	}
	
	@Override 
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map<String, Object> referenceData = new HashMap<String, Object>();
		List<Receipt> receipts = receiptsService.getLastFewReceipts();
		Map<String,String> sizeUnitTypes = SizeUnitType.getKvpMap();
		referenceData.put("sizeUnitTypes", sizeUnitTypes);
		referenceData.put("receipts", receipts);
		return referenceData;
	}

	public void setReceiptItemsService(ReceiptItemsService receiptItemsService) {
		this.receiptItemsService = receiptItemsService;
	}

	public void setReceiptsService(ReceiptsService receiptsService) {
		this.receiptsService = receiptsService;
	}

	public ReceiptItemCounter getReceiptItemCounter() {
		return receiptItemCounter;
	}

	public void setReceiptItemCounter(ReceiptItemCounter receiptItemCounter) {
		this.receiptItemCounter = receiptItemCounter;
	}
}
