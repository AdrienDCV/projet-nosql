package com.fisa.producerapi.exceptions;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "error.business.default-message";
  public static final HttpStatus DEFAULT_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
  private HttpStatus status;

  public BusinessException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public BusinessException(String message) {
    super(message);
    this.status = DEFAULT_STATUS;
  }

  public BusinessException() {
    super(DEFAULT_MESSAGE);
    this.status = DEFAULT_STATUS;
  }

  public HttpStatus getStatus(){
    return this.status;
  }

}
