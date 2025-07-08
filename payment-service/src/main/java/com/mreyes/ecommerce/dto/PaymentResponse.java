package com.mreyes.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentResponse implements Serializable {

  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  Long id;
  Long orderId;
  Double amount;
  String method;
  String status;
}