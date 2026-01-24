package com.fisa.clientapi.exceptions;

import org.springframework.http.HttpStatus;

public class NotEnoughStockAvailableException extends BusinessException {

  private static final String DEFAULT_MESSAGE = "error.client-orders.not-enough-stock-available";
  private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

  public NotEnoughStockAvailableException() {  super(DEFAULT_MESSAGE, HTTP_STATUS); }
  public NotEnoughStockAvailableException(String message) {
    super(message);
  }
}
