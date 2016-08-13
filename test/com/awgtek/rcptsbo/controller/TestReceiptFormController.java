package com.awgtek.rcptsbo.controller;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import com.awgtek.rcptsbo.domain.Receipt;
import com.awgtek.rcptsbo.domain.Store;

import junit.framework.TestCase;

public class TestReceiptFormController extends TestCase {
	private ApplicationContext ac;

	public void setUp() {
		ac = new FileSystemXmlApplicationContext(
				"test/WEB-INF/test-springapp-servlet.xml");
	}
	
	public void testOnSubmit() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(new MockHttpSession());
		
		Receipt receipt = new Receipt();
		receipt.setDateOfPurchase(new Date());
		receipt.setStore(new Store(1, "Carrefour", "123 Main"));
		
		ReceiptFormController rfc = (ReceiptFormController) ac.getBean("receiptFormController");
		rfc.onSubmit(request, null, receipt, null);
	}
}
