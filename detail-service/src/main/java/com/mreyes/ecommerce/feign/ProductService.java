package com.mreyes.ecommerce.feign;

import com.mreyes.ecommerce.dto.product.ProductResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", configuration = com.mreyes.ecommerce.config.FeignConfig.class)
public interface ProductService {

  @GetMapping(value = "/api/v1/products", consumes = "application/json", produces = "application/json")
  List<ProductResponse> getAllProducts();

  @GetMapping(value = "/api/v1/products/{id}", consumes = "application/json", produces = "application/json")
  ProductResponse getProductById(@PathVariable Long id);

}
