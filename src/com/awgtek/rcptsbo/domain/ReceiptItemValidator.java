package com.awgtek.rcptsbo.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ReceiptItemValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(ReceiptItem.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ReceiptItem receiptItem = (ReceiptItem) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genericName", 
				"error.not-specified", 
				new String[] {"Generic Name"}, 
				"Value required.");
		
		if (receiptItem.getGenericName() != null && 
				receiptItem.getGenericName().length() > 70) {
			errors.rejectValue("genericName", "error.too-long", 
					new String[] {
							String.valueOf(receiptItem.getGenericName().length()), 
							"70"}, 
					"too long");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "totalCost", "error.not-specified", 
				new String[] {"Total Cost"}, 
				"Value required.");
		
		   if("-1".equals(String.valueOf(receiptItem.getReceiptId()))){
			errors.rejectValue("receiptId", "error.not-specified", 
					new String[] {"Receipt"}, 
					"Value required.");
		   }
		   
/*		   if("NONE".equals(receiptItem.getSizeUnitType())){
			errors.rejectValue("sizeUnitType", "error.not-specified", 
					new String[] {"Size Unit Type"}, 
					"Value required.");
		   }
*/	
	}

}