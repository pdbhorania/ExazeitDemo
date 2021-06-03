package com.demo.client.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.client.entity.Client;

public interface ClientRepository extends JpaRepository<Client, String>{

	Client findByMobileNumber(Long mobileNumber);
	
	List<Client> findByFirstName(String firstName);
}
