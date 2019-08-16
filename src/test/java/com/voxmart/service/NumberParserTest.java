package com.voxmart.service;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.voxmart.service.NumberParser;
import com.voxmart.util.VoxmartUtil;


@RunWith(SpringRunner.class)
public class NumberParserTest {
	
	Map<String, Integer> countryCodes;
	
	NumberParser numberParser;
	
	Map<String, String> prefixes;
	
	@Before
	public void setUp() {
		countryCodes = VoxmartUtil.mapCountryCodeAssociateWithTheCountryCallingCodes();
		prefixes= VoxmartUtil.mapCountryCodeAssociateWithTheNationalTrunkPrefixes();
		numberParser = new NumberParser(countryCodes, prefixes);
	}

	@Test
	public void shouldParserNationalNumberIntoInternational() {
		String dialledNumberBeforeFormat = "02079460056";
	  	String userNumber = "+441614960148";
	  	String dialledNumberExpected="+442079460056";
	  	String dialledNumberCurrent=numberParser.parse(dialledNumberBeforeFormat, userNumber);	  	
	  	assertEquals(dialledNumberExpected, dialledNumberCurrent);
	}
	
	@Test
	public void shouldParserKeepInternationalNumber() {
		String dialledNumberBeforeFormat = "+442079460056";
	  	String userNumber = "+441614960148";
	  	String dialledNumberExpected="+442079460056";
	   	String dialledNumberCurrent=numberParser.parse(dialledNumberBeforeFormat, userNumber);	  	
	  	assertEquals(dialledNumberExpected, dialledNumberCurrent);
	}	
	
	@Test
	public void shouldCheckTwoPhoneNumberToBeTheSameByCountryCallingCodeAreTheSame() {
		String dialledNumber1 = "02079460056";
	  	String dialledNumber2="+442079460056";
	  	boolean checkExpected=true;
	   	boolean checkCurrent=numberParser.checkPhoneNumberAreTheSame(dialledNumber1, dialledNumber2);	  	
	  	assertEquals(checkExpected, checkCurrent);
	}	
	
}
