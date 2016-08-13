package com.awgtek.rcptsbo.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.awgtek.rcptsbo.domain.Store;

import junit.framework.TestCase;

public class TestStoreFormController extends TestCase {
	private ApplicationContext ac;

	public void setUp() {
		ac = new FileSystemXmlApplicationContext(
				"test/WEB-INF/test-springapp-servlet.xml");
	}
	
	public void testOnSubmit() throws Exception {
		Store store = new Store();
		
		store.setName("some good store");
		store.setAddress("123 main street, spfd mo");
		
		StoreFormController controller = (StoreFormController) ac.getBean("storeFormController");
		ModelAndView mav = controller.onSubmit(null, null, store, null);
		assertEquals(RedirectView.class, mav.getView().getClass());
		assertEquals("store.htm", ((RedirectView)mav.getView()).getUrl());
		assertTrue(controller.getStoreService().getStoreDao().getStores().contains(store));
	}
}
