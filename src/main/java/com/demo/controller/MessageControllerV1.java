package com.demo.controller;


import com.demo.model.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Message Controller V1.
 */
@RestController
@Api(value = "Get messages", description = "Message REST API")
public class MessageControllerV1 extends AbstractMessageController {

  /**
   * This controller version.
   */
  private static final String VERSION = "v1";

  @Override
  @RequestMapping(value = "/" + VERSION + "/messages/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
  @ResponseBody
  @ApiOperation(value = "Get message", notes = "Get a message given an id")
  public final Message getMessage(@PathVariable(value = "id") final String id) {
    return new Message(id, getDefaultMessage());
  }

  @Override
  public String getVersion() {
    return VERSION;
  }
}
