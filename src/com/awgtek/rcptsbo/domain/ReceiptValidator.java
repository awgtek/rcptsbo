package com.awgtek.rcptsbo.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ReceiptValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(Receipt.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Receipt receipt = (Receipt) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfPurchase", "error.not-specified", 
				new String[] {"Date"}, 
				"Value required.");
		
		   if(receipt.getStore() == null){
			errors.rejectValue("store", "error.not-specified", 
					new String[] {"Store"}, 
					"Value required.");
		   }
	}

}
