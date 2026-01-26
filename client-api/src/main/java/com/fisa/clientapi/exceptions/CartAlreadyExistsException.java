package com.fisa.clientapi.exceptions;

import org.springframework.http.HttpStatus;

public class CartAlreadyExistsException extends BusinessException {

  private static final String DEFAULT_MESSAGE = "error.carts.already-exists";
  private static final HttpStatus HTTP_STATUS = HttpStatus.CONFLICT;

  public CartAlreadyExistsException() {  super(DEFAULT_MESSAGE, HTTP_STATUS); }
  public CartAlreadyExistsException(String message) {
    super(message);
  }
}
