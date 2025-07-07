package com.mreyes.auth.service;

import com.mreyes.auth.dto.auth.AuthRequest;
import com.mreyes.auth.dto.user.UserRequest;

public interface AuthService {

  void register(UserRequest userRequest);

  String authenticate(AuthRequest authRequest);

}
