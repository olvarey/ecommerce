package com.mreyes.ecommerce.mapper;

import com.mreyes.ecommerce.dto.order.OrderRequest;
import com.mreyes.ecommerce.dto.order.OrderResponse;
import com.mreyes.ecommerce.model.Order;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface OrderMapper {

  Order toEntity(OrderRequest orderRequest);

  OrderResponse toDto(Order order);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Order update(OrderRequest orderRequest, @MappingTarget Order order);
}