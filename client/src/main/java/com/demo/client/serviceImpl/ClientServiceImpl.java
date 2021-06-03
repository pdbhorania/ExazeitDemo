package com.demo.client.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.client.converter.ClientConverter;
import com.demo.client.dto.ClientDto;
import com.demo.client.dto.ClientSearch;
import com.demo.client.entity.Client;
import com.demo.client.repository.ClientRepository;
import com.demo.client.service.ClientService;
import com.demo.client.util.ClientUtil;
import com.demo.client.util.Constants;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientConverter converter;

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public String addClient(ClientDto clientDto) {
		commonValidation(clientDto);
		validateDuplicateIdNumber(clientDto.getIdNumber());
		Client clientEntityToSave = converter.convertDtoToEntity(clientDto);
		clientRepository.save(clientEntityToSave);
		return Constants.CREATE_SUCCESS;
	}

	@Override
	public String updateClient(ClientDto clientDto) {
		validateClientExistance(clientDto.getIdNumber());
		commonValidation(clientDto);
		Client clientEntityToSave = converter.convertDtoToEntity(clientDto);
		clientRepository.save(clientEntityToSave);
		return Constants.UPDATE_SUCCESS;
	}

	@Override
	public List<ClientDto> searchClient(ClientSearch clientSearch) {
		List<Client> clients = new ArrayList<Client>();
		Client client = new Client();
		if (StringUtils.isNotBlank(clientSearch.getFirstName())) {
			clients = clientRepository.findByFirstName(clientSearch.getFirstName());
		} else if (StringUtils.isNotBlank(clientSearch.getIdNumber())) {
			client = clientRepository.findById(clientSearch.getIdNumber()).orElse(null);
			clients.add(client);
		} else if (clientSearch.getMobileNumber() != null) {
			 client = clientRepository.findByMobileNumber(clientSearch.getMobileNumber());
			 clients.add(client);
		} else {
			throw new RuntimeException(Constants.VALID_INPUT_ERROR);
		}
		if(clients == null || clients.size() == 0 ) {
			throw new RuntimeException(Constants.CLIENT_DOES_NOT_EXIST);
		}
		return converter.convertEntityToDto(clients);
	}

	private void commonValidation(ClientDto clientDto) {
		ClientUtil.validateIdNumber(clientDto.getIdNumber());		
		validateDuplicateMobileNumber(clientDto.getMobileNumber());
	}

	private void validateDuplicateIdNumber(String id) {
		Client client = clientRepository.findById(id).orElse(null);
		if (client != null) {
			throw new RuntimeException(Constants.DUPLICATE_ID_ERROR);
		}
	}

	private void validateDuplicateMobileNumber(Long mobileNumber) {
		if (mobileNumber != null) {
			Client client = clientRepository.findByMobileNumber(mobileNumber);
			if (client != null) {
				throw new RuntimeException(Constants.DUPLICATE_MOBILE_ERROR);
			}
		}
	}

	private void validateClientExistance(String id) {
		Boolean exist = clientRepository.existsById(id);
		if (!exist) {
			throw new RuntimeException(Constants.CLIENT_DOES_NOT_EXIST);
		}
	}

}
