package com.mreyes.ecommerce.repository;

import com.mreyes.ecommerce.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}