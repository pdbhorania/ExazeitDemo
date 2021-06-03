package com.demo.client.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
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
import com.demo.client.util.Constants;

@RunWith(PowerMockRunner.class)
public class ClientServiceTest {

	@InjectMocks
	private ClientServiceImpl clientService;

	@Mock
	private ClientRepository clientRepository;

	@Mock
	private ClientConverter clientConverter;

	@Spy
	private ClientUtil clientUtil = new ClientUtil();

	@Test
	public void addClientTest() {
		ClientDto clientDto = getClientDto();
		Client client = getClient();
		Mockito.when(clientRepository.save(client)).thenReturn(null);
		Optional<Client> nullClient = Optional.ofNullable(null);
		Mockito.when(clientRepository.findById(clientDto.getIdNumber())).thenReturn(nullClient);
		Mockito.when(clientRepository.findByMobileNumber(clientDto.getMobileNumber())).thenReturn(null);
		Mockito.when(clientConverter.convertDtoToEntity(clientDto)).thenReturn(client);
		String response = clientService.addClient(clientDto);
		assertNotNull(response);
		assertEquals(Constants.CREATE_SUCCESS, response);
		InOrder order = Mockito.inOrder(clientRepository, clientUtil, clientConverter);

		order.verify(clientRepository, Mockito.times(1)).findByMobileNumber(clientDto.getMobileNumber());
		order.verify(clientRepository, Mockito.times(1)).findById(clientDto.getIdNumber());
		order.verify(clientConverter, Mockito.times(1)).convertDtoToEntity(clientDto);
		order.verify(clientRepository, Mockito.times(1)).save(client);
	}

	@Test(expected = RuntimeException.class)
	public void addClientTest_InvalidIdLength() {
		ClientDto clientDto = getClientDto();
		clientDto.setIdNumber("123");
		clientService.addClient(clientDto);
	}

	@Test(expected = RuntimeException.class)
	public void addClientTest_MobileNumberAlreadyExist() {
		ClientDto clientDto = getClientDto();
		Client client = getClient();
		Mockito.when(clientRepository.findByMobileNumber(clientDto.getMobileNumber())).thenReturn(client);
		clientService.addClient(clientDto);
	}

	@Test(expected = RuntimeException.class)
	public void addClientTest_IDAlreadyExist() {
		ClientDto clientDto = getClientDto();
		Client client = getClient();
		Optional<Client> nullClient = Optional.ofNullable(client);
		Mockito.when(clientRepository.findById(clientDto.getIdNumber())).thenReturn(nullClient);
		Mockito.when(clientRepository.findByMobileNumber(clientDto.getMobileNumber())).thenReturn(null);
		clientService.addClient(clientDto);
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

	private Client getClient() {
		Client client = new Client();
		client.setFirstName("Exazit");
		client.setLastName("Exazit");
		client.setIdNumber("9201010001000");
		client.setMobileNumber(99999999999L);
		client.setPhysicalAddress("Mumbai");
		return client;
	}

//likewise we can have update and search test cases

}
