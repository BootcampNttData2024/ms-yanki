package com.vasquez.msyanki.business.exception;

import lombok.Data;


import java.time.LocalDateTime;

/**
 * Error response.
 *
 * @author Vasquez
 * @version 1.0.
 */
@Data
public class ErrorResponse {

  private LocalDateTime timestamp;
  private int code;
  private String error;
  private String message;

  /**
   * Default constructor.
   *
   * @param code    code
   *
   * @param error   error
   *
   * @param message message
   */
  public ErrorResponse(int code, String error, String message) {
    this.timestamp = LocalDateTime.now();
    this.code = code;
    this.error = error;
    this.message = message;
  }

}
