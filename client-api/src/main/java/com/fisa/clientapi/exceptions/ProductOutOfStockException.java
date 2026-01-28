package com.fisa.clientapi.exceptions;

import org.springframework.http.HttpStatus;

public class ProductOutOfStockException extends BusinessException {

  private static final String DEFAULT_MESSAGE = "error.client-orders.out-of-stock";
  private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

  public ProductOutOfStockException() {  super(DEFAULT_MESSAGE, HTTP_STATUS); }
  public ProductOutOfStockException(String message) {
    super(message);
  }
}
