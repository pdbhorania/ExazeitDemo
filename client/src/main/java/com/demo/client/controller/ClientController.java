package com.demo.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.client.dto.ClientDto;
import com.demo.client.dto.ClientSearch;
import com.demo.client.service.ClientService;

@RestController
@RequestMapping("v1/client")
@Validated
public class ClientController {
	
	 @Autowired
	 private ClientService clientService;

	 @PostMapping(value = "/add")
	  public String addClient(@RequestBody ClientDto clientDto) {
	    return clientService.addClient(clientDto);
	  }
	 
	 @PostMapping(value = "/update")
	  public String updateClient(@RequestBody ClientDto clientDto) {
	    return clientService.updateClient(clientDto);
	  }
	 
	 @PostMapping(value = "/search")
	  public List<ClientDto> searchClient(@RequestBody ClientSearch clientSearch) {
	    return clientService.searchClient(clientSearch);
	  }

}
