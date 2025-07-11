package com.mreyes.ecommerce.service.implementation;

import com.mreyes.ecommerce.dto.auth.AuthRequest;
import com.mreyes.ecommerce.dto.user.UserRequest;
import com.mreyes.ecommerce.exception.UserNameAlreadyRegisteredException;
import com.mreyes.ecommerce.jwt.JwtService;
import com.mreyes.ecommerce.mapper.UserMapper;
import com.mreyes.ecommerce.model.User;
import com.mreyes.ecommerce.repository.UserRepository;
import com.mreyes.ecommerce.service.AuthService;
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
  private final JwtService jwtService;
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

    return jwtService.generateToken(user.getUsername(), user.getRole());
  }
}
