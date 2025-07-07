package com.mreyes.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_order_detail", nullable = false, unique = true)
  private Long id;
  @Column(name = "order_id", nullable = false)
  private Long orderId;
  @Column(name = "product_id", nullable = false)
  private Long productId;
  @Column(name = "quantity", nullable = false)
  private Integer quantity;
  @Column(name = "price", nullable = false)
  private Double price;

}