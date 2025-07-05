package com.mreyes.ecommerce.service.implementation;

import com.mreyes.ecommerce.dto.ClientRequest;
import com.mreyes.ecommerce.dto.ClientResponse;
import com.mreyes.ecommerce.mapper.ClientMapper;
import com.mreyes.ecommerce.model.Client;
import com.mreyes.ecommerce.repository.ClientRepository;
import com.mreyes.ecommerce.service.IClient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService implements IClient {

  private final ClientRepository clientRepository;
  private final ClientMapper clientMapper;

  @Override
  public ClientResponse save(ClientRequest clientRequest) {
    return clientMapper.toDto(clientRepository.save(clientMapper.toEntity(clientRequest)));
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
}
