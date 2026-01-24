package com.fisa.clientapi.exceptions;

import org.springframework.http.HttpStatus;

public class ClientOrderNotFoundException extends BusinessException {

  private static final String DEFAULT_MESSAGE = "error.orders.not-found";
  private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

  public ClientOrderNotFoundException() {  super(DEFAULT_MESSAGE, HTTP_STATUS); }
  public ClientOrderNotFoundException(String message) {
    super(message);
  }
}
