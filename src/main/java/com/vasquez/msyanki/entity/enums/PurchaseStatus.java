package com.vasquez.msyanki.entity.enums;

import lombok.Getter;

/**
 * PurchaseStatus enum.
 *
 * @author Vasquez
 * @version 1.0.
 */
@Getter
public enum PurchaseStatus {

  REQUESTED("SOLICITADO"),
  PENDING_ACCEPTANCE("PENDIENTE DE ACEPTACION"),
  NONE("NINGUNO");
  private String value;

  PurchaseStatus(String value) {
    this.value = value;
  }

}
