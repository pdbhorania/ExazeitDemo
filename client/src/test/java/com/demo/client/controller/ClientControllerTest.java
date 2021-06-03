package com.demo.client.controller;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.demo.client.dto.ClientDto;
import com.demo.client.dto.ClientSearch;
import com.demo.client.service.ClientService;

@RunWith(PowerMockRunner.class)
public class ClientControllerTest {
	
	@InjectMocks
	ClientController controller;
	
	@Mock
	ClientService clientService;
	
	@Test
	public void addClientTest() {
		ClientDto dto = new ClientDto();
		Mockito.when(clientService.addClient(dto)).thenReturn(Mockito.anyString());
		String response= controller.addClient(dto);
		assertNotNull(response);
		Mockito.verify(clientService,Mockito.times(1)).addClient(dto);
	}
	
	@Test
	public void updateClientTest() {
		ClientDto dto = new ClientDto();
		Mockito.when(clientService.updateClient(dto)).thenReturn(Mockito.anyString());
		String response= controller.updateClient(dto);
		assertNotNull(response);
		Mockito.verify(clientService,Mockito.times(1)).updateClient(dto);
	}
	
	@Test
	public void searchClientTest() {
		ClientSearch clientSearch = new ClientSearch();
		Mockito.when(clientService.searchClient(clientSearch)).thenReturn(Mockito.anyList());
		List<ClientDto> response= controller.searchClient(clientSearch);
		assertNotNull(response);
		Mockito.verify(clientService,Mockito.times(1)).searchClient(clientSearch);
	}

}
