package com.mreyes.ecommerce.service;

import com.mreyes.ecommerce.dto.ClientRequest;
import com.mreyes.ecommerce.dto.ClientResponse;
import java.util.List;

public interface IClient {

  ClientResponse save(ClientRequest clientRequest);

  ClientResponse findById(Long id);

  List<ClientResponse> findAll();

  ClientResponse update(Long id, ClientRequest clientRequest);

  void delete(Long id);


}
