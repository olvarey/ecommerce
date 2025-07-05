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
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client extends BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "first_name", nullable = false, length = 150)
  private String firstName;
  @Column(name = "last_name", nullable = false, length = 150)
  private String lastName;
  @Column(name = "email", nullable = false, unique = true, length = 150)
  private String email;
  @Column(name = "phone_number", nullable = false, length = 20)
  private String phoneNumber;
  @Column(name = "address", nullable = false, length = 500)
  private String address;

}
