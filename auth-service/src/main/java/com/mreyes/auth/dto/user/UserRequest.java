package com.mreyes.auth.dto.user;

import java.io.Serializable;
import lombok.Value;

/**
 * DTO for {@link com.mreyes.auth.model.User}
 */
@Value
public class UserRequest implements Serializable {

  String username;
  String password;
  String role;
}