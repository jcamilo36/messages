package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * It represents a message.
 */
@Data
@AllArgsConstructor
public class Message implements Serializable {
  /**
   * Serial version uid.
   */
  private static final long serialVersionUID = 1707104334276011718L;

  /**
   * Identifier.
   */
  private String id;

  /**
   * Message.
   */
  private String message;
}
