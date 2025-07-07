package com.mreyes.ecommerce.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

  @Override
  public void apply(RequestTemplate template) {
    var context = SecurityContextHolder.getContext();
    var auth = context.getAuthentication();
    if (auth != null && auth.getCredentials() != null) {
      template.header("Authorization", "Bearer " + auth.getCredentials().toString());
    }
  }
}
