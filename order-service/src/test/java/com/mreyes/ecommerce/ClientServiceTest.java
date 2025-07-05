package com.mreyes.ecommerce;

import com.mreyes.ecommerce.feign.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ClientService.class)
class ClientServiceTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ClientService clientService;

  @Test
  void testGetAllClients() {
  }

}