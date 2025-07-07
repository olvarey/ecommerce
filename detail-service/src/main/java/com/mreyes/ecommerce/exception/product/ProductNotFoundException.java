package com.mreyes.ecommerce.exception.product;

/**
 * ProductNotFoundException is a custom runtime exception used to indicate that a product with the
 * specified ID was not found in the system.
 */
public class ProductNotFoundException extends RuntimeException {

  /**
   * Constructs a new ProductNotFoundException with a detailed error message.
   *
   * @param id The ID of the product that was not found.
   */
  public ProductNotFoundException(Long id) {
    super("Product with ID " + id + " not found");
  }
}