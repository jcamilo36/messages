package com.demo.exception;

import lombok.Data;

/**
 * Error message response.
 */
@Data
public class ErrorResponse {
  /**
   * Message error.
   */
  private String message;

  /**
   * Status error.
   */
  private int status;
}
