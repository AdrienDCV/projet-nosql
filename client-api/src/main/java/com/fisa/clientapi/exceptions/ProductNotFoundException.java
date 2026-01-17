package com.fisa.clientapi.exceptions;

import org.springframework.http.HttpStatus;

/**
 * This class represents the exception that is thrown when the email is already used.
 */
public class ProductNotFoundException extends BusinessException {

  private static final String DEFAULT_MESSAGE = "error.products.not-found";
  private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

  public ProductNotFoundException() {  super(DEFAULT_MESSAGE, HTTP_STATUS); }
  public ProductNotFoundException(String message) {
    super(message);
  }
}
