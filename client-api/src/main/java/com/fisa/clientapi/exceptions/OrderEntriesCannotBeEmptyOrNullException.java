package com.fisa.clientapi.exceptions;

import org.springframework.http.HttpStatus;

public class OrderEntriesCannotBeEmptyOrNullException extends BusinessException {

  private static final String DEFAULT_MESSAGE = "error.orders.order-entries-cannot-be-empty-or-null";
  private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

  public OrderEntriesCannotBeEmptyOrNullException() {  super(DEFAULT_MESSAGE, HTTP_STATUS); }
  public OrderEntriesCannotBeEmptyOrNullException(String message) {
    super(message);
  }
}
