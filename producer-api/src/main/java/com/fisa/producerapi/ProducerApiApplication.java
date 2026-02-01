package com.fisa.producerapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
				info = @Info(
								title = "Producer API",
								version = "1.0",
								description = "Producer API documentation and endpoints testing"
				)
)
@SpringBootApplication
public class ProducerApiApplication {

  public static void main(String[] args) {
		SpringApplication.run(ProducerApiApplication.class, args);
	}

}
