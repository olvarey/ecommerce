package com.mreyes.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentRequest implements Serializable {

  Long orderId;
  Double amount;
  String method;
  String status;
}