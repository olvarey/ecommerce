package com.mreyes.ecommerce.dto.order;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Value;

/**
 * DTO for {@link com.mreyes.ecommerce.model.Order}
 */
@Value
public class OrderResponse implements Serializable {

  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  Long id;
  Long clientId;
  String status;
}