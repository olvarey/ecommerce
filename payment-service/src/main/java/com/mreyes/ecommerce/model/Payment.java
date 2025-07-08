package com.mreyes.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment extends BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_payment", nullable = false)
  private Long id;
  @Column(name = "order_id", nullable = false)
  private Long orderId;
  @Column(name = "amount", nullable = false)
  private Double amount;
  @Column(name = "method", nullable = false)
  private String method;
  @Column(name = "status", nullable = false)
  private String status;

}