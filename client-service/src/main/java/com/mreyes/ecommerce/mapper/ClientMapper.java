package com.mreyes.ecommerce.mapper;

import com.mreyes.ecommerce.dto.ClientRequest;
import com.mreyes.ecommerce.dto.ClientResponse;
import com.mreyes.ecommerce.model.Client;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface ClientMapper {

  Client toEntity(ClientRequest clientRequest);

  ClientResponse toDto(Client client);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Client update(ClientRequest clientRequest, @MappingTarget Client client);
}