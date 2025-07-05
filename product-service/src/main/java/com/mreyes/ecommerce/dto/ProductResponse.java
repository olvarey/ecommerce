package com.mreyes.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * ProductResponse is a data transfer object (DTO) used to encapsulate product details in responses
 * from the e-commerce application. It supports serialization and deserialization.
 */
@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse {

  private Long id;
  private String title;
  private String description;
  private Double price;
  private String category;
  private String image;
  @JsonProperty(namespace = "rating")
  private RatingResponse rating;
}
