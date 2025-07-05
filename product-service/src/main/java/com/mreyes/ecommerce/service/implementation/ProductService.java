package com.mreyes.ecommerce.service.implementation;

import com.mreyes.ecommerce.dto.ProductResponse;
import com.mreyes.ecommerce.feign.ProductClient;
import com.mreyes.ecommerce.service.IProduct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ProductService is a service implementation that provides methods to interact with product-related
 * data by delegating calls to the ProductClient. It implements the IProduct interface.
 */
@Service
@RequiredArgsConstructor
public class ProductService implements IProduct {

  private final ProductClient productClient;

  /**
   * Retrieves a list of all products by delegating the call to the ProductClient.
   *
   * @return A list of ProductResponse objects containing product details.
   */
  @Override
  public List<ProductResponse> getAllProducts() {
    return productClient.getAllProducts();
  }

  /**
   * Retrieves the details of a specific product by its ID by delegating the call to the
   * ProductClient.
   *
   * @param id The unique identifier of the product to fetch.
   * @return A ProductResponse object containing the details of the requested product.
   */
  @Override
  public ProductResponse getProductById(Long id) {
    return productClient.getProductById(id);
  }
}