package com.demo.client.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Client")
@Data
public class Client {
	
	@Id
	@Column
	private	String idNumber;
	
	@Column
	private	String firstName;
	
	@Column
	private	String lastName;
	
	@Column
	private Long mobileNumber;
	
	@Column
	private	String physicalAddress;

}
