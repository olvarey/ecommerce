package com.mreyes.ecommerce.feign;

import com.mreyes.ecommerce.dto.client.ClientRequest;
import com.mreyes.ecommerce.dto.client.ClientResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "client-service")
public interface ClientService {

  @GetMapping(value = "/api/v1/clients", produces = "application/json")
  List<ClientResponse> getAllClients();

  @GetMapping(value = "/api/v1/clients/{id}", produces = "application/json")
  ClientResponse getClientById(@PathVariable("id") Long id);

  @PostMapping(value = "/api/v1/clients", consumes = "application/json", produces = "application/json")
  ClientResponse createClient(@RequestBody ClientRequest clientRequest);

  @PutMapping(value = "/api/v1/clients/{id}", consumes = "application/json", produces = "application/json")
  ClientResponse updateClient(@PathVariable("id") Long id,
      @RequestBody ClientRequest clientRequest);

  @DeleteMapping(value = "/api/v1/clients/{id}")
  void deleteClient(@PathVariable("id") Long id);
}
