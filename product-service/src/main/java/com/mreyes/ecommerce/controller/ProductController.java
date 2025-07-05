package com.mreyes.ecommerce.controller;

import com.mreyes.ecommerce.dto.ProductResponse;
import com.mreyes.ecommerce.exception.ProductNotFoundException;
import com.mreyes.ecommerce.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling product-related API endpoints. Provides endpoints to retrieve all
 * products or a specific product by ID.
 */
@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

  private final Logger logger = LoggerFactory.getLogger(ProductController.class);
  private final ProductService productService;

  /**
   * Endpoint to retrieve all products.
   *
   * @return ResponseEntity containing a list of ProductResponse objects.
   */
  @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ProductResponse>> getAllProducts() {
    List<ProductResponse> products = productService.getAllProducts();
    logger.info("Retrieved {} products", products != null ? products.size() : 0);
    return ResponseEntity.ok(products);
  }

  /**
   * Endpoint to retrieve a product by its ID.
   *
   * @param id The ID of the product to retrieve.
   * @return ResponseEntity containing the ProductResponse object for the specified ID.
   */
  @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
    ProductResponse product = productService.getProductById(id);
    logger.info("Retrieved {} product with ID {}", product, id);
    if (product == null || product.getId() == null) {
      throw new ProductNotFoundException(id);
    }
    return ResponseEntity.ok(product);
  }
}