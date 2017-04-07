package com.demo.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

/**
 * Test class for {@link MessageControllerV1}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class MessageControllerV1Test {

  /**
   * Default message literal.
   */
  private static final String DEFAULT_MESSAGE = "Default message literal";

  /**
   * Mock MVC for Unit Testing.
   */
  private MockMvc mvc;

  /**
   * Expected exception.
   */
  @Rule
  public ExpectedException exception = ExpectedException.none();

  /**
   * Message controller with injected mocks.
   */
  @InjectMocks
  private MessageControllerV1 messageController;

  @Before
  public void setUp() {
    mvc = MockMvcBuilders.standaloneSetup(messageController).build();
    Whitebox.setInternalState(messageController, AbstractMessageController.DEFAULT_MESSAGE_FIELD,
        DEFAULT_MESSAGE);
  }

  /**
   * Test the GET message operation with a given id.
   * @throws Exception if there is an error when processing the call
   */
  @Test
  public void getMessageProperParam() throws Exception {
    String id = "1234";
    MvcResult result = mvc.perform(get(String.format("/v1/messages/%s", id))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name()))
        .andExpect(status().isOk())
        .andReturn();
    String payload = result.getResponse().getContentAsString();
    assertTrue(payload.contains(String.format("\"id\":\"%s\"", id)));
    assertTrue(payload.contains(String.format("\"message\":\"%s\"", DEFAULT_MESSAGE)));
  }

  /**
   * Test the GET message operation returns a 404 error if the handler is incorrect.
   * @throws Exception If there is an error when processing the call
   */
  @Test
  public void getMessageWrongHandler() throws Exception {
    mvc.perform(get("/incorrect")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name()))
        .andExpect(status().is4xxClientError())
        .andReturn();
  }

  /**
   * Test the GET message operation returns a 404 error if the id is missing.
   * @throws Exception If there is an error when processing the call
   */
  @Test
  public void getMessageMissingId() throws Exception {
    mvc.perform(get("/v1/messages")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name()))
        .andExpect(status().is4xxClientError())
        .andReturn();
  }
}
