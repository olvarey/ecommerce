package com.mreyes.auth.service.implementation;

import com.mreyes.auth.dto.auth.AuthRequest;
import com.mreyes.auth.dto.user.UserRequest;
import com.mreyes.auth.exception.UserNameAlreadyRegisteredException;
import com.mreyes.auth.jwt.JwtUtil;
import com.mreyes.auth.mapper.UserMapper;
import com.mreyes.auth.model.User;
import com.mreyes.auth.repository.UserRepository;
import com.mreyes.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);
  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;

  @Override
  public void register(UserRequest userRequest) {
    if (userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
      throw new UserNameAlreadyRegisteredException("Username already exists");
    }
    User user = userMapper.toEntity(userRequest, passwordEncoder);
    userRepository.save(user);
  }

  @Override
  public String authenticate(AuthRequest authRequest) {
    User user = userRepository.findByUsername(authRequest.getUsername())
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
      throw new BadCredentialsException("Invalid username or password");
    }

    return jwtUtil.generateToken(user.getUsername(), user.getRole());
  }
}
