package com.voxmart.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.voxmart.exception.CustomException;


public class VoxmartUtil {
	
	private VoxmartUtil() {}
	
	public static String getLocalCountry() {
		Locale currentLocale = Locale.getDefault();
		return currentLocale.getCountry();				
	}
	
	public static int getLengthOfTelephoneNumberByCountry(String country) {
		if(country == null)
			throw new CustomException("Could not get the country");
		String paese = country.toUpperCase();
		int lengthTelephoneCountry=0;
		switch(paese) {
			case "GB": lengthTelephoneCountry = 10; break;
			case "US": lengthTelephoneCountry = 10; break;
			default:break;			
		}
		return lengthTelephoneCountry;	
	}
	
	public static Map<String , Integer> mapCountryCodeAssociateWithTheCountryCallingCodes(){
		Map<String, Integer> countryCodesAssCountryCallingCodes = new HashMap<>();
		countryCodesAssCountryCallingCodes.put("GB", 44);
		countryCodesAssCountryCallingCodes.put("US", 1);
		return countryCodesAssCountryCallingCodes;
		
	}
	
	public static Map<String , String> mapCountryCodeAssociateWithTheNationalTrunkPrefixes(){
		Map<String, String> countryCodesAssNationalTrunkPrefixes = new HashMap<>();
		countryCodesAssNationalTrunkPrefixes.put("GB", "0");
		countryCodesAssNationalTrunkPrefixes.put("US", "1");
		return countryCodesAssNationalTrunkPrefixes;
		
	}

}
