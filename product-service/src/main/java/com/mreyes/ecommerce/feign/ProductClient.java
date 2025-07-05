package com.mreyes.ecommerce.feign;

import com.mreyes.ecommerce.dto.ProductResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ProductClient is a Feign client interface used to interact with the product-service API. It
 * provides methods to fetch product details from the external service.
 */
@FeignClient(name = "product-service", url = "https://fakestoreapi.com")
public interface ProductClient {

  /**
   * Fetches a list of all products from the product-service API.
   *
   * @return A list of ProductResponse objects containing product details.
   */
  @GetMapping("/products")
  List<ProductResponse> getAllProducts();

  /**
   * Fetches the details of a specific product by its ID from the product-service API.
   *
   * @param id The unique identifier of the product to fetch.
   * @return A ProductResponse object containing the details of the requested product.
   */
  @GetMapping("/products/{id}")
  ProductResponse getProductById(@PathVariable("id") Long id);

}