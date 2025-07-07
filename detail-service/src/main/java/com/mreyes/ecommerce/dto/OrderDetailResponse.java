package com.mreyes.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mreyes.ecommerce.dto.order.OrderResponse;
import com.mreyes.ecommerce.dto.product.ProductResponse;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDetailResponse implements Serializable {

  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  Long id;
  OrderResponse order;
  ProductResponse product;
  Integer quantity;
  Double price;
}