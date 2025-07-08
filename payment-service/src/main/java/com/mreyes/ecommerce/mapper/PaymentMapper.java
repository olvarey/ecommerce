package com.mreyes.ecommerce.mapper;

import com.mreyes.ecommerce.dto.PaymentRequest;
import com.mreyes.ecommerce.dto.PaymentResponse;
import com.mreyes.ecommerce.model.Payment;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface PaymentMapper {

  Payment toEntity(PaymentRequest paymentRequest);

  PaymentResponse toDto(Payment payment);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Payment update(PaymentRequest paymentRequest, @MappingTarget Payment payment);
}