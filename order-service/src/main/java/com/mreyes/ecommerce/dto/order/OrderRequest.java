package com.mreyes.ecommerce.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest implements Serializable {

  Long id;
  Long clientId;
  String status;
}