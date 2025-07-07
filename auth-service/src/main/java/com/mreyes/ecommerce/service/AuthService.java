package com.mreyes.ecommerce.service;

import com.mreyes.ecommerce.dto.auth.AuthRequest;
import com.mreyes.ecommerce.dto.user.UserRequest;

public interface AuthService {

  void register(UserRequest userRequest);

  String authenticate(AuthRequest authRequest);

}
