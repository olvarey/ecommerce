package com.mreyes.ecommerce.feign;

import com.mreyes.ecommerce.dto.order.OrderResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service")
public interface OrderService {

  @GetMapping(value = "/api/v1/orders", produces = "application/json")
  List<OrderResponse> getAllOrders();

  @GetMapping(value = "/api/v1/orders/with-client", produces = "application/json")
  List<OrderResponse> getAllOrdersWithClient();

  @GetMapping(value = "/api/v1/orders/{id}", produces = "application/json")
  OrderResponse getOrderById(@PathVariable("id") Long id);

}
