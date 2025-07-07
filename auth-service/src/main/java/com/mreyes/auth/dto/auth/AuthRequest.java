package com.mreyes.auth.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthRequest implements Serializable {

  String username;
  String password;
}