package com.fisa.producerapi.exceptions.producers;

import com.fisa.producerapi.exceptions.BusinessException;
import org.springframework.http.HttpStatus;

public class ProducerNotFoundException extends BusinessException {

  private static final String DEFAULT_MESSAGE = "error.producers.not-found";
  private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

  public ProducerNotFoundException() {  super(DEFAULT_MESSAGE, HTTP_STATUS); }
  public ProducerNotFoundException(String message) {
    super(message);
  }
}
