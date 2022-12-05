package org.tlc.microservices.marketdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tlc.microservices.marketdataservice.model.RedisMessage;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "org.tlc.*")
public class MarketDataServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MarketDataServiceApplication.class, args);
	}
}
