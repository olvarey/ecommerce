package com.mreyes.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;


/**
 * ProductRequest is a data transfer object (DTO) used to encapsulate product details for requests
 * in the e-commerce application. It supports serialization and deserialization.
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequest implements Serializable {

  private Long id;
  private String title;
  private String description;
  private Double price;
  private String category;
  private String image;
}
