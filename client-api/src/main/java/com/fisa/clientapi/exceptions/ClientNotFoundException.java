package com.fisa.clientapi.exceptions;

import org.springframework.http.HttpStatus;

public class ClientNotFoundException extends BusinessException {

  private static final String DEFAULT_MESSAGE = "error.clients.not-found";
  private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

  public ClientNotFoundException() {  super(DEFAULT_MESSAGE, HTTP_STATUS); }
  public ClientNotFoundException(String message) {
    super(message);
  }
}
