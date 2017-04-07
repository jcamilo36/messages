package com.demo.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

/**
 * Abstract message controller for all versions.
 */
@Getter
public abstract class AbstractMessageController implements IMessageController {

  /**
   * Name of the default message field.
   */
  public static final String DEFAULT_MESSAGE_FIELD = "defaultMessage";

  /**
   * Default message literal.
   */
  @Value("${messages.literals.default}")
  private String defaultMessage;

  /**
   * Get the version from the current controller implementation.
   * @return the version of this implementation
   */
  public abstract String getVersion();
}
