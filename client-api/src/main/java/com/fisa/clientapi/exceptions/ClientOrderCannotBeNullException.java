package com.fisa.clientapi.exceptions;

import org.springframework.http.HttpStatus;

public class ClientOrderCannotBeNullException extends BusinessException {

  private static final String DEFAULT_MESSAGE = "error.orders.client-order-cannot-be-null";
  private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

  public ClientOrderCannotBeNullException() {  super(DEFAULT_MESSAGE, HTTP_STATUS); }
  public ClientOrderCannotBeNullException(String message) {
    super(message);
  }
}
