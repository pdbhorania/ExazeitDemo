package com.demo.client.util;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class ClientUtil {

	
	public static void validateIdNumber(String id) {
		if(id.length() != 13) {
			throw new RuntimeException("Please enter valid Id");
		} 
		
		validateYYMMDD(id);
		validateGender(id);
		validateCitizen(id);
	}
	
	private static void validateYYMMDD(String id) {
		
		try {
			new SimpleDateFormat("yymmdd").parse(id.substring(0, 6));
		} catch (Exception e) {
			throw new RuntimeException("Please enter valid Id");
		}
	}
	
	private static void validateGender(String id) {
		if(!Pattern.matches("^\\d{4}$", id.substring(6, 10))) {
			throw new RuntimeException("Please enter valid Id");
		}
	}
	
	private static void validateCitizen(String id) {
		if(!Pattern.matches("\\d{0,1}", id.substring(8, 9))) {
			throw new RuntimeException("Please enter valid Id");
		}
	}


	
	
}
