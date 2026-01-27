package com.fisa.clientapi.exceptions;

import org.springframework.http.HttpStatus;

public class CartEntryNotFoundException extends BusinessException {

  private static final String DEFAULT_MESSAGE = "error.cart-entries.not-found";
  private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

  public CartEntryNotFoundException() {  super(DEFAULT_MESSAGE, HTTP_STATUS); }
  public CartEntryNotFoundException(String message) {
    super(message);
  }
}
