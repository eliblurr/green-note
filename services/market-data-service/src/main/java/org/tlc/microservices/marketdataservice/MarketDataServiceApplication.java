package org.tlc.microservices.marketdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "org.tlc.*")
@EnableCaching
public class MarketDataServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MarketDataServiceApplication.class, args);
	}
}
