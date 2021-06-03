package com.demo.client.service;

import java.util.List;

import com.demo.client.dto.ClientDto;
import com.demo.client.dto.ClientSearch;

public interface ClientService {

	String addClient(ClientDto clientDto);

	String updateClient(ClientDto clientDto);

	List<ClientDto> searchClient(ClientSearch clientSearch);

}
