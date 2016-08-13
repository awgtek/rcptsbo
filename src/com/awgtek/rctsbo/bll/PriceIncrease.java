package com.awgtek.rctsbo.bll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PriceIncrease {
	private final static Logger logger = LoggerFactory.getLogger(PriceIncrease.class);

	private int percentage;
	
	public void setPercentage(int i) {
		percentage = i;
		logger.info("Percentage set to " + i);
	}
	
	public int getPercentage() {
		return percentage;
	}
}
