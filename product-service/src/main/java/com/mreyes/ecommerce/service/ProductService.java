package com.mreyes.ecommerce.service;

import com.mreyes.ecommerce.dto.ProductResponse;
import java.util.List;

/**
 * ProductServiceImpl is an interface that defines the contract for product-related operations. It provides
 * methods to retrieve product data.
 */
public interface ProductService {

  /**
   * Retrieves a list of all products.
   *
   * @return A list of ProductResponse objects containing product details.
   */
  public List<ProductResponse> getAllProducts();

  /**
   * Retrieves the details of a specific product by its ID.
   *
   * @param id The unique identifier of the product to fetch.
   * @return A ProductResponse object containing the details of the requested product.
   */
  public ProductResponse getProductById(Long id);
}