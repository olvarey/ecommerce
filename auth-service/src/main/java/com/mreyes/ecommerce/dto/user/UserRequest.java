package com.mreyes.ecommerce.dto.user;

import com.mreyes.ecommerce.model.User;
import java.io.Serializable;
import lombok.Value;

/**
 * DTO for {@link User}
 */
@Value
public class UserRequest implements Serializable {

  String username;
  String password;
  String role;
}