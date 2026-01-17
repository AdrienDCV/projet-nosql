package com.fisa.producerapi.exceptions;

import org.springframework.http.HttpStatus;

/**
 * This class represents the exception that is thrown when the email is already used.
 */
public class EmailAlreadyUsedException extends BusinessException {

  private static final String DEFAULT_MESSAGE = "error.authentication.email-already-used";
  private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

  public EmailAlreadyUsedException() {  super(DEFAULT_MESSAGE, HTTP_STATUS); }
  public EmailAlreadyUsedException(String message) {
    super(message);
  }
}
