package com.mreyes.auth.controller;

import com.mreyes.auth.dto.auth.AuthRequest;
import com.mreyes.auth.dto.user.UserRequest;
import com.mreyes.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody UserRequest request) {
    authService.register(request);
    return ResponseEntity.ok("User registered");
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody AuthRequest request) {
    String token = authService.authenticate(request);
    return ResponseEntity.ok(token);
  }

}
