package com.mreyes.ecommerce.feign;

import com.mreyes.ecommerce.dto.client.ClientResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-service")
public interface ClientService {

  @GetMapping(value = "/api/v1/clients", produces = "application/json")
  List<ClientResponse> getAllClients();

  @GetMapping(value = "/api/v1/clients/{id}", produces = "application/json")
  ClientResponse getClientById(@PathVariable("id") Long id);

}
