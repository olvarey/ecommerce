package com.mreyes.ecommerce.dto.user;

import com.mreyes.ecommerce.model.User;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Value;

/**
 * DTO for {@link User}
 */
@Value
public class UserResponse implements Serializable {

  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  Long id;
  String username;
  String role;
}