package com.awgtek.rctsbo.bll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PriceIncreaseValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(PriceIncreaseValidator.class);
	
	private int DEFAULT_MIN_PERCENTAGE = 0;
	private int DEFAULT_MAX_PERCENTAGE = 50;
	private int minPercentage = DEFAULT_MIN_PERCENTAGE;
	private int maxPercentage = DEFAULT_MAX_PERCENTAGE;

	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(PriceIncrease.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		PriceIncrease pi = (PriceIncrease) obj;
		if (pi == null) {
			errors.rejectValue("percentage", "error.not-specified", new String[] {"Percentage"}, 
					"Value required.");
		}
		else {
			logger.info("Validating with " + pi + ": " + pi.getPercentage());
			if (pi.getPercentage() > maxPercentage) {
				errors.rejectValue("percentage", "error.too-high", 
						new Object[] { new Integer(maxPercentage)}, "Value too high.");
			}
			if (pi.getPercentage() <= minPercentage) {
				errors.rejectValue("percentage", "error.too-low", 
						new Object[] {new Integer(minPercentage)}, "Value too low.");
			}
		}
	}

    public void setMinPercentage(int i) {
        minPercentage = i;
    }

    public int getMinPercentage() {
        return minPercentage;
    }

    public void setMaxPercentage(int i) {
        maxPercentage = i;
    }

    public int getMaxPercentage() {
        return maxPercentage;
    }

}
