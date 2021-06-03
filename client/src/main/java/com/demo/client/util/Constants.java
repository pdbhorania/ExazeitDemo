package com.demo.client.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Constants {

	 public static final List<String> CITIZEN =
		      Collections.unmodifiableList(Arrays.asList("0", "1"));
	 public static final String DUPLICATE_ID_ERROR="Id already exist";
	 public static final String DUPLICATE_MOBILE_ERROR="Mobile number already exist";
	 public static final String CREATE_SUCCESS="Client created successfully";
	 public static final String UPDATE_SUCCESS="Client updated successfully";
	 public static final String CLIENT_DOES_NOT_EXIST="Client does not exist";
	 public static final String VALID_INPUT_ERROR="Please enter valid input";
}
