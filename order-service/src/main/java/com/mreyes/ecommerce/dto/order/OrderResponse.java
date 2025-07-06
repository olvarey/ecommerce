package com.mreyes.ecommerce.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mreyes.ecommerce.dto.client.ClientResponse;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponse implements Serializable {

  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  Long id;
  Long clientId;
  String status;
  @JsonProperty("clientInfo")
  ClientResponse clientResponse;
}