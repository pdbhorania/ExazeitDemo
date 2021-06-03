package com.demo.client.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.demo.client.converter.ClientConverter;
import com.demo.client.dto.ClientDto;
import com.demo.client.entity.Client;
import com.demo.client.repository.ClientRepository;
import com.demo.client.serviceImpl.ClientServiceImpl;
import com.demo.client.util.ClientUtil;

@RunWith(PowerMockRunner.class)
public class ClientServiceTest {
	
	@InjectMocks
	private ClientServiceImpl clientService;
	
	@Mock
	private ClientRepository clientRepository;
	
	@Mock
	private ClientConverter clientConverter;
	
	@Mock
	private ClientUtil clientUtil;
	
	
	@Test
	public void addClientTest() {
		ClientDto clientDto = getClientDto();
		Client client = new Client();
		Mockito.when(clientRepository.save(client)).thenReturn(Mockito.any());
		Mockito.doNothing().when(clientUtil).validateIdNumber(clientDto.getIdNumber());
		Mockito.when(clientRepository.findByMobileNumber(clientDto.getMobileNumber())).
		String response= clientService.addClient(clientDto);
		assertNotNull(response);
		Mockito.verify(clientRepository,Mockito.times(1)).save(client);
	}
	
	private ClientDto getClientDto() {
		ClientDto clientDto = new ClientDto();
		clientDto.setFirstName("Exazit");
		clientDto.setLastName("Exazit");
		clientDto.setIdNumber("9201010001000");
		clientDto.setMobileNumber(99999999999L);
		clientDto.setPhysicalAddress("Mumbai");
		return clientDto;
	}
	
	@Test
	public void updateClientTest() {
		Client client = new Client();
		Mockito.when(clientRepository.save(client)).thenReturn(Mockito.any());
		Client response= clientRepository.save(client);
		assertNotNull(response);
		Mockito.verify(clientRepository,Mockito.times(1)).save(client);
	}
	
	@Test
	public void searchClientTest() {
		Client client = new Client();
		Mockito.when(clientRepository.findByFirstName(client.getFirstName())).thenReturn(Mockito.anyList());
		List<Client> response= clientRepository.findByFirstName(client.getFirstName());
		assertNotNull(response);
		Mockito.verify(clientRepository,Mockito.times(1)).findByFirstName(client.getFirstName());
	}

}
