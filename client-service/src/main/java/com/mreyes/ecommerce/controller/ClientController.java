package com.mreyes.ecommerce.controller;

import com.mreyes.ecommerce.dto.ClientRequest;
import com.mreyes.ecommerce.dto.ClientResponse;
import com.mreyes.ecommerce.service.ClientService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clients")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ClientController {

  private final ClientService clientService;

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<ClientResponse>> getAllClients() {
    List<ClientResponse> clients = clientService.findAll();
    if (clients.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(clients);
  }

  @GetMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity<ClientResponse> getClientById(@PathVariable("id") Long id) {
    ClientResponse clientResponse = clientService.findById(id);
    return ResponseEntity.ok(clientResponse);
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<ClientResponse> createClient(@RequestBody ClientRequest clientRequest) {
    ClientResponse createdClient = clientService.save(clientRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
  }

  @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
  public ResponseEntity<ClientResponse> updateClient(@PathVariable("id") Long id,
      @RequestBody ClientRequest clientRequest) {
    ClientResponse updatedClient = clientService.update(id, clientRequest);
    return ResponseEntity.ok(updatedClient);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) {
    clientService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
