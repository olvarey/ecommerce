package com.mreyes.auth.dto.user;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Value;

/**
 * DTO for {@link com.mreyes.auth.model.User}
 */
@Value
public class UserResponse implements Serializable {

  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  Long id;
  String username;
  String role;
}