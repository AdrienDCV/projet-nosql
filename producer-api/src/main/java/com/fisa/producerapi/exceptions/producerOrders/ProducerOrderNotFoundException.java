package com.fisa.producerapi.exceptions.producerOrders;

import com.fisa.producerapi.exceptions.BusinessException;
import org.springframework.http.HttpStatus;

public class ProducerOrderNotFoundException extends BusinessException {

  private static final String DEFAULT_MESSAGE = "error.producer-orders.not-found";
  private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

  public ProducerOrderNotFoundException() {  super(DEFAULT_MESSAGE, HTTP_STATUS); }
  public ProducerOrderNotFoundException(String message) {
    super(message);
  }
}
