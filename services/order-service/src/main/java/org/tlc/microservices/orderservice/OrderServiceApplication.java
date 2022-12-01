package org.tlc.microservices.orderservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import org.tlc.microservices.orderservice.dto.enums.Side;

@SpringBootApplication
public class OrderServiceApplication{

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
