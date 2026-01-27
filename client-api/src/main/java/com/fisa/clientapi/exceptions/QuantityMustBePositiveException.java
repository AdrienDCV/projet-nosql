package com.fisa.clientapi.exceptions;

import org.springframework.http.HttpStatus;

public class QuantityMustBePositiveException extends BusinessException {

  private static final String DEFAULT_MESSAGE = "error.cart-entries.quantity-must-be-positive";
  private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

  public QuantityMustBePositiveException() {  super(DEFAULT_MESSAGE, HTTP_STATUS); }
  public QuantityMustBePositiveException(String message) {
    super(message);
  }
}
