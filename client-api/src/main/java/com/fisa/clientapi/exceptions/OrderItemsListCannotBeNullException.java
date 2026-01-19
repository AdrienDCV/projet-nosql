package com.fisa.clientapi.exceptions;

import org.springframework.http.HttpStatus;

public class OrderItemsListCannotBeNullException extends BusinessException {

  private static final String DEFAULT_MESSAGE = "error.client-orders.client-order-cannot-be-null";
  private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

  public OrderItemsListCannotBeNullException() {  super(DEFAULT_MESSAGE, HTTP_STATUS); }
  public OrderItemsListCannotBeNullException(String message) {
    super(message);
  }
}
