package com.awgtek.rcptsbo.init;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import junit.framework.TestCase;

public class DbUpgraderTest extends TestCase {
	private ApplicationContext ac;


	protected void setUp() throws Exception {
		ac = new FileSystemXmlApplicationContext(
				"test/WEB-INF/test-int-dbupgrade.xml");
	}

	public void testExecute() {
		//fail("Not yet implemented");
	}

}
