package com.mreyes.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = "com.mreyes.ecommerce")
@EnableJpaAuditing
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.mreyes.ecommerce")
public class DetailServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(DetailServiceApplication.class, args);
  }

}
