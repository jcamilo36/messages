package com.demo.controller;

import com.demo.model.Message;

/**
 * A controller that returns a {@link Message} object given an identifier.
 */
public interface IMessageController {

  /**
   * Get a message object given an identifier.
   * @param id Given identifier
   * @return a {@link Message} object
   */
  Message getMessage(final String id);
}
