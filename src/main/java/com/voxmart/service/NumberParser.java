package com.voxmart.service;

import java.util.Map;

import com.voxmart.exception.CustomException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NumberParser {  	
	
	Map<String, Integer> countryCodes;
	
	Map<String, String> prefixes;
	
	public NumberParser(Map<String, Integer> countryCodes, Map<String, String> prefixes) {
		this.countryCodes = countryCodes;
		this.prefixes = prefixes;		
	}
	
    public String parse(String dialledNumber, String userNumber) {  
    	log.info("Class NumberParser - method parse()");
    	String dialledNumFormated =null;
    	try {
	    	if(dialledNumber == null || userNumber==null) 
	    		throw new CustomException("Could not get dialedNumber or userNumber");
	    	
	    	dialledNumFormated = formatDialledPhoneNumberIntoInternationalFormat(countryCodes, prefixes, dialledNumber);
	    	log.info("dialledNumberFormated: "+ dialledNumFormated);
    	}catch(Exception e) {
    		log.error("- ERROR while handle dialledNumFormated - ");
    	}
    	return dialledNumFormated;
    }
    
    public String formatDialledPhoneNumberIntoInternationalFormat(Map<String, Integer> countryCodes, Map<String, String> prefixes, String dialledNumber) {   
		log.info("Class NumberParser - method convertInternationalNumberIntoNationalNumber()");
		String numberPhone="";
    	try {
    		if(countryCodes ==null || prefixes ==null || dialledNumber ==null)
    			throw new CustomException("Could not get countryCodes or prefixes");
    		    		
    		if(!dialledNumber.startsWith("+") && !dialledNumber.startsWith("-") && !dialledNumber.startsWith("*") && !dialledNumber.startsWith("/")) {
    			numberPhone = handleNationalNumber(countryCodes, prefixes, dialledNumber);    			
    		}else if(dialledNumber.startsWith("+")) {
    			numberPhone = dialledNumber;
    		}else {
    			throw new CustomException("International number must start with +");
    		}
    	}catch(Exception e){
    		log.error("- ERROR while handle phone number - ");
    	}    	    	
    	return numberPhone;
    }

	public String handleNationalNumber(Map<String, Integer> countryCodes, Map<String, String> prefixes, String dialledNumber) {
		final String sign ="+";		
		String nationalTrunkPrefix =dialledNumber.substring(0, dialledNumber.length()-10);
		Integer countryCallingCodePrefix=identifyCallingCode(countryCodes, prefixes, nationalTrunkPrefix);
		String number =dialledNumber.substring(dialledNumber.length()-10, dialledNumber.length());
		return sign + countryCallingCodePrefix + number;			

	}

	private Integer identifyCallingCode(Map<String , Integer> countryCodes, Map<String, String> prefixes, final String nationalTrunkPrefix) {
		boolean done=false;
		Integer countryCallingCode=0;
		for (Map.Entry<String, String> entryTrunkPrefix : prefixes.entrySet()) {			
				  if(nationalTrunkPrefix.equals(entryTrunkPrefix.getValue()) && !done){
					  for (Map.Entry<String, Integer> entryCountryCodes : countryCodes.entrySet()) {
						  if(entryTrunkPrefix.getKey().equals(entryCountryCodes.getKey())){
							  countryCallingCode = entryCountryCodes.getValue();
							  done=true;	
							  break;
						  }
					  }
				  }			
		  }
		return countryCallingCode;
	}
    
    public boolean checkPhoneNumberAreTheSame(String dialledNumber1, String dialledNumber2) {
    	boolean check=false;
    	
    	if(!dialledNumber1.startsWith("-") && !dialledNumber1.startsWith("*") && !dialledNumber1.startsWith("/") && 
    	   !dialledNumber2.startsWith("-") && !dialledNumber2.startsWith("*") && !dialledNumber2.startsWith("/")) {
    		if (identifyCallingCode(countryCodes, prefixes, dialledNumber1).equals(identifyCallingCode(countryCodes, prefixes, dialledNumber2)) ) {
        		check = true;
        	}else {
        		check = false;
        	}
    	}else {
    		throw new CustomException("International number must start with +");
    	}
    	
    	return check;
    }
    
}