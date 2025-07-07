package com.mreyes.ecommerce.mapper;

import com.mreyes.ecommerce.dto.OrderDetailRequest;
import com.mreyes.ecommerce.dto.OrderDetailResponse;
import com.mreyes.ecommerce.model.OrderDetail;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface OrderDetailMapper {

  OrderDetail toEntity(OrderDetailRequest orderDetailRequest);

  OrderDetailResponse toDto(OrderDetail orderDetail);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  OrderDetail update(OrderDetailRequest orderDetailRequest, @MappingTarget OrderDetail orderDetail);
}