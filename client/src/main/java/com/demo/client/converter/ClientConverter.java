package com.demo.client.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.demo.client.dto.ClientDto;
import com.demo.client.entity.Client;

@Component
public class ClientConverter {

	public Client convertDtoToEntity(ClientDto clientDto) {
		Client client = new Client();
		if (clientDto != null) {
			client.setIdNumber(clientDto.getIdNumber());
			client.setFirstName(clientDto.getFirstName());
			client.setLastName(clientDto.getLastName());
			client.setMobileNumber(clientDto.getMobileNumber());
			client.setPhysicalAddress(clientDto.getPhysicalAddress());
		}
		return client;
	}

	public List<ClientDto> convertEntityToDto(List<Client> clients) {
		List<ClientDto> clientDtos = new ArrayList<ClientDto>();
		if (CollectionUtils.isNotEmpty(clients)) {
			clients.forEach(c->{
				ClientDto clientDto= new ClientDto();
				clientDto.setIdNumber(c.getIdNumber());
				clientDto.setFirstName(c.getFirstName());
				clientDto.setLastName(c.getLastName());
				clientDto.setMobileNumber(c.getMobileNumber());
				clientDto.setPhysicalAddress(c.getPhysicalAddress());
				clientDtos.add(clientDto);
				
			});
			
		}
		return clientDtos;
		
		
	}
}
