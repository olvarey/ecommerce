package com.mreyes.ecommerce.service.implementation;

import com.mreyes.ecommerce.dto.ProductResponse;
import com.mreyes.ecommerce.feign.FakeStoreClient;
import com.mreyes.ecommerce.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ProductServiceImpl is a service implementation that provides methods to interact with
 * product-related data by delegating calls to the FakeStoreClient. It implements the
 * ProductServiceImpl interface.
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final FakeStoreClient fakeStoreClient;

  /**
   * Retrieves a list of all products by delegating the call to the FakeStoreClient.
   *
   * @return A list of ProductResponse objects containing product details.
   */
  @Override
  public List<ProductResponse> getAllProducts() {
    return fakeStoreClient.getAllProducts();
  }

  /**
   * Retrieves the details of a specific product by its ID by delegating the call to the
   * FakeStoreClient.
   *
   * @param id The unique identifier of the product to fetch.
   * @return A ProductResponse object containing the details of the requested product.
   */
  @Override
  public ProductResponse getProductById(Long id) {
    return fakeStoreClient.getProductById(id);
  }
}