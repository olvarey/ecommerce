package com.mreyes.ecommerce.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

/**
 * RatingResponse is a data transfer object (DTO) used to encapsulate rating details in responses
 * from the e-commerce application. It supports serialization and deserialization.
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatingResponse implements Serializable {

  private Double rate;
  private Long count;

}
