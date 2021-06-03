package com.demo.client.dto;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

	@NonNull
	private	String firstName;
	@NonNull
	private	String lastName;
	private Long mobileNumber;
	@NonNull
	private	String idNumber;
	private	String physicalAddress;
}
