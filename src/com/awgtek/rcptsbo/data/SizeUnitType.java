package com.awgtek.rcptsbo.data;

import java.util.HashMap;
import java.util.Map;

public enum SizeUnitType {

	KG("kilograms"),
	LBS("pounds"),
	ML("milliliters"),
	G("grams"),
	MG("milligrams"),
	IU("IU"),
	OZ("ounces"),
	GAL("gallons"),
	L("liters"),
	MCG("micrograms"),
	DP("dry pints"),
	CUP("cups"),
	P("pints"),
	Y("yards"),
	TPSHEET("toilet paper sheet");
	
	SizeUnitType(String label) {
		this.label = label;
	}

	private String label;
	
	public static Map<String, String> getKvpMap() {
		Map<String, String> kvpMap = new HashMap<String, String>();
		for (SizeUnitType sut: SizeUnitType.values()) {
			kvpMap.put(sut.name(), sut.label);
		}
		return kvpMap;
	}
}
