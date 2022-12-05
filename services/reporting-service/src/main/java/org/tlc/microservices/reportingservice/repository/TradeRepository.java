package org.tlc.microservices.reportingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tlc.microservices.reportingservice.model.Trade;

public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
