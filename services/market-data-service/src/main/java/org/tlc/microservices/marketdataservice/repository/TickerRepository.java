package org.tlc.microservices.marketdataservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tlc.microservices.marketdataservice.model.Ticker;

public interface TickerRepository extends MongoRepository<Ticker,String> {
}
