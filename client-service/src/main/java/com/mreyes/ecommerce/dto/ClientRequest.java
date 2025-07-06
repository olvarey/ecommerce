package com.mreyes.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientRequest implements Serializable {

  String firstName;
  String lastName;
  String email;
  String phoneNumber;
  String address;
}