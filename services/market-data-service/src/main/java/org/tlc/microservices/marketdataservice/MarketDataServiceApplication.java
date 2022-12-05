package org.tlc.microservices.marketdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tlc.microservices.marketdataservice.model.RedisMessage;


@SpringBootApplication
public class MarketDataServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MarketDataServiceApplication.class, args);
	}
}
