package com.mreyes.ecommerce.controller;

import com.mreyes.ecommerce.dto.OrderDetailRequest;
import com.mreyes.ecommerce.dto.OrderDetailResponse;
import com.mreyes.ecommerce.service.OrderDetailService;
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
@RequestMapping("/api/v1/order-details")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OrderDetailController {

  private final OrderDetailService orderDetailService;

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<OrderDetailResponse>> getAllOrderDetails() {
    List<OrderDetailResponse> orderDetails = orderDetailService.findAll();
    if (orderDetails.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(orderDetails);
  }

  @GetMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity<OrderDetailResponse> getOrderDetailById(@PathVariable Long id) {
    OrderDetailResponse orderDetailResponse = orderDetailService.findById(id);
    return ResponseEntity.ok(orderDetailResponse);
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<OrderDetailResponse> createOrderDetail(
      @RequestBody OrderDetailRequest orderDetailRequest) {
    OrderDetailResponse createdOrderDetail = orderDetailService.save(orderDetailRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDetail);
  }

  @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
  public ResponseEntity<OrderDetailResponse> updateOrderDetail(@PathVariable Long id,
      @RequestBody OrderDetailRequest orderDetailRequest) {
    OrderDetailResponse updatedOrderDetail = orderDetailService.update(id, orderDetailRequest);
    return ResponseEntity.ok(updatedOrderDetail);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long id) {
    orderDetailService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
