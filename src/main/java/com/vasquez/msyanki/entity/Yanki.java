package com.vasquez.msyanki.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Yanki entity.
 *
 * @author Vasquez
 * @version 1.0.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document(collection = "yanki")
public class Yanki {

  @Id
  private String yankiId;

  private String documentType;

  private String documentNumber;

  private String phoneNumber;

  private String imeiNumber;

  private String email;

  private Double balance;

  private String associatedDebitCard;

}
