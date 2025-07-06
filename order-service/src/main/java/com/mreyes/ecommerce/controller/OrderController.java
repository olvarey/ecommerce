package com.mreyes.ecommerce.controller;

import com.mreyes.ecommerce.dto.order.OrderRequest;
import com.mreyes.ecommerce.dto.order.OrderResponse;
import com.mreyes.ecommerce.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<OrderResponse>> getAllOrders() {
    List<OrderResponse> orders = orderService.findAll();
    if (orders.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(orders);
  }

  @GetMapping(value = "/with-client", produces = "application/json")
  public ResponseEntity<List<OrderResponse>> getAllOrdersWithClient() {
    List<OrderResponse> orders = orderService.findAllWithClient();
    if (orders.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(orders);
  }

  @GetMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity<OrderResponse> getOrderById(@PathVariable("id") Long id) {
    OrderResponse orderResponse = orderService.findById(id);
    return ResponseEntity.ok(orderResponse);
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
    OrderResponse createdOrder = orderService.save(orderRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
  }

  @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
  public ResponseEntity<OrderResponse> updateOrder(@PathVariable("id") Long id,
      @RequestBody OrderRequest orderRequest) {
    OrderResponse updatedOrder = orderService.update(id, orderRequest);
    return ResponseEntity.ok(updatedOrder);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
    orderService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
