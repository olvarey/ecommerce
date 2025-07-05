package com.mreyes.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientResponse implements Serializable {

  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  Long id;
  String firstName;
  String lastName;
  String email;
  String phoneNumber;
  String address;
}