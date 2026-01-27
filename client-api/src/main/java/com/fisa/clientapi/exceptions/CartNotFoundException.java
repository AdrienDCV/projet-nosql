package com.fisa.clientapi.exceptions;

import org.springframework.http.HttpStatus;

public class CartNotFoundException extends BusinessException {

  private static final String DEFAULT_MESSAGE = "error.carts.not-found";
  private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

  public CartNotFoundException() {  super(DEFAULT_MESSAGE, HTTP_STATUS); }
  public CartNotFoundException(String message) {
    super(message);
  }
}
