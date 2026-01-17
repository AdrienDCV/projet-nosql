package com.fisa.producerapi.exceptions.products;

import com.fisa.producerapi.exceptions.BusinessException;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends BusinessException {

  private static final String DEFAULT_MESSAGE = "error.products.not-found";
  private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

  public ProductNotFoundException() {  super(DEFAULT_MESSAGE, HTTP_STATUS); }
  public ProductNotFoundException(String message) {
    super(message);
  }
}
