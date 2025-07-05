package com.mreyes.ecommerce;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mreyes.ecommerce.controller.ProductController;
import com.mreyes.ecommerce.service.implementation.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
class ProductControllerTest {


  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private ProductService productService;

  @Test
  void getAllProducts_shouldReturnListOfProducts() throws Exception {
    mockMvc.perform(get("/products").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
  }

  @Test
  void getProductById_shouldReturnProduct() throws Exception {
    mockMvc.perform(get("/products/1").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}