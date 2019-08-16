package com.voxmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.Bean;

import com.voxmart.service.NumberParser;
import com.voxmart.util.VoxmartUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class VoxmartApplication {
		
	@Bean
	NumberParser numberParser() {
		return new NumberParser(VoxmartUtil.mapCountryCodeAssociateWithTheCountryCallingCodes(), VoxmartUtil.mapCountryCodeAssociateWithTheNationalTrunkPrefixes());
	}
	
    public static void main(String[] args) {
    	ApplicationContext context = SpringApplication.run(VoxmartApplication.class);
        NumberParser parser =context.getBean(NumberParser.class);
        
        String dialledNumber1 = "02079460056";
	  	String userNumber1 = "+441614960148";
	  	String numberParsed=parser.parse(dialledNumber1, userNumber1);
	  	log.info("dialledNumber: "+dialledNumber1 +" numberParsed: "+numberParsed);	
	  	
	  	log.info("");
	  	String dialledNumber2 = "+442079460056";
	  	String userNumber2 = "+441614960148";	  
	  	String numberParsed2=parser.parse(dialledNumber2, userNumber2);
	  	log.info("dialledNumber2: "+dialledNumber2 +" numberParsed2: "+numberParsed2);
	  	
	  	log.info("");
	  	String dialledNumber3 ="02079460056"; 
	  	String dialledNumber4 ="+442079460056";
	  	boolean check= false;
	  	try {
	  		check = parser.checkPhoneNumberAreTheSame(dialledNumber3, dialledNumber4);
	  	}catch(Exception e) {
	  		log.info("International number must start with +");
	  	}
	  	log.info("dialledNumber3: "+dialledNumber3 +" dialledNumber4: "+dialledNumber4 + " are the same country "+ check );	  	
    }
}