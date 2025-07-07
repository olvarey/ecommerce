package com.mreyes.auth.mapper;

import com.mreyes.auth.dto.user.UserRequest;
import com.mreyes.auth.dto.user.UserResponse;
import com.mreyes.auth.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface UserMapper {

  @Mapping(target = "password", expression = "java(passwordEncoder.encode(userRequest.getPassword()))")
  User toEntity(UserRequest userRequest, @Context PasswordEncoder passwordEncoder);

  UserResponse toDto(User user);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  User update(UserRequest userRequest, @MappingTarget User user);
}