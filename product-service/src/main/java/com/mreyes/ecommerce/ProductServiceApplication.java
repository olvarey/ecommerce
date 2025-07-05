package com.mreyes.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ProductServiceApplication is the main entry point for the product-service application. It is a
 * Spring Boot application that enables service discovery and Feign clients for communication with
 * other microservices.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ProductServiceApplication {

  /**
   * The main method that starts the Spring Boot application.
   *
   * @param args Command-line arguments passed to the application.
   */
  public static void main(String[] args) {
    SpringApplication.run(ProductServiceApplication.class, args);
  }

}