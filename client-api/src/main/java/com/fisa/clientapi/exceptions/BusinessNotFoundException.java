package com.fisa.clientapi.exceptions;

import org.springframework.http.HttpStatus;

public class BusinessNotFoundException extends BusinessException {

  private static final String DEFAULT_MESSAGE = "error.businesses.not-found";
  private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

  public BusinessNotFoundException() {  super(DEFAULT_MESSAGE, HTTP_STATUS); }
  public BusinessNotFoundException(String message) {
    super(message);
  }
}
