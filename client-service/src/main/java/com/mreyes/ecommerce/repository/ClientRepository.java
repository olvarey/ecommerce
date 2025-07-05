package com.mreyes.ecommerce.repository;

import com.mreyes.ecommerce.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

  boolean existsByEmail(String email);
}