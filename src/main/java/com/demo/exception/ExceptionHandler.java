package com.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * Exception handler.
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandler {

  /**
   * Content type header value.
   */
  private static final String CONTENT_TYPE_HEADER = "Content-Type";

  /**
   * Method to handle missing param exceptions.
   * @param request HTTP Request
   * @param ex Exception thrown
   * @return response entity
   */
  @org.springframework.web.bind.annotation.ExceptionHandler(NoHandlerFoundException.class)
  ResponseEntity<ErrorResponse> handleError404(final HttpServletRequest request,
                                final Exception ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage("The requested resource does not exist.");
    errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(CONTENT_TYPE_HEADER,MediaType.APPLICATION_JSON_UTF8_VALUE);
    return new ResponseEntity<>(errorResponse, headers, HttpStatus.NOT_FOUND);
  }

  /**
   * Method to handle an internal server error.
   * @param request HTTP Request
   * @param ex Exception thrown
   * @return response entity
   */
  @org.springframework.web.bind.annotation.ExceptionHandler(Throwable.class)
  ResponseEntity<ErrorResponse> handleError500(final HttpServletRequest request,
                                               final Exception ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage("An unexpected error happened. Please contact the administrator.");
    log.error(ex.getLocalizedMessage(), ex);
    errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(CONTENT_TYPE_HEADER,MediaType.APPLICATION_JSON_UTF8_VALUE);
    return new ResponseEntity<>(errorResponse, headers, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
