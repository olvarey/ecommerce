package com.mreyes.ecommerce.service.implementation;

import com.mreyes.ecommerce.dto.ClientRequest;
import com.mreyes.ecommerce.dto.ClientResponse;
import com.mreyes.ecommerce.exception.EmailAlreadyExistsException;
import com.mreyes.ecommerce.mapper.ClientMapper;
import com.mreyes.ecommerce.model.Client;
import com.mreyes.ecommerce.repository.ClientRepository;
import com.mreyes.ecommerce.service.ClientService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceServiceImpl implements ClientService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceServiceImpl.class);

  private final ClientRepository clientRepository;
  private final ClientMapper clientMapper;

  @Override
  public ClientResponse save(ClientRequest clientRequest) {
    if (clientRepository.existsByEmail(clientRequest.getEmail())) {
      LOGGER.warn("Email already exists: {}", clientRequest.getEmail());
      throw new EmailAlreadyExistsException(clientRequest.getEmail());
    }
    Client client = clientMapper.toEntity(clientRequest);
    return clientMapper.toDto(clientRepository.save(client));
  }

  @Override
  public ClientResponse findById(Long id) {
    return clientMapper.toDto(clientRepository.findById(id).orElseThrow());
  }

  @Override
  public List<ClientResponse> findAll() {
    return clientRepository.findAll().stream().map(clientMapper::toDto).toList();
  }

  @Override
  public ClientResponse update(Long id, ClientRequest clientRequest) {
    Client selectedClient = clientRepository.findById(id).orElseThrow();
    Client updatedClient = clientMapper.update(clientRequest, selectedClient);
    return clientMapper.toDto(clientRepository.save(updatedClient));
  }

  @Override
  public void delete(Long id) {
    Client client = clientRepository.findById(id).orElseThrow();
    clientRepository.delete(client);

  }

  @Override
  public boolean existsByEmail(String email) {
    return clientRepository.existsByEmail(email);
  }
}
