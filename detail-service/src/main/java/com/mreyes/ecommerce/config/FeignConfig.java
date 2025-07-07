package com.mreyes.ecommerce.config;

import com.mreyes.ecommerce.feign.FeignClientInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

  @Bean
  public RequestInterceptor requestInterceptor() {
    return new FeignClientInterceptor();
  }

}
