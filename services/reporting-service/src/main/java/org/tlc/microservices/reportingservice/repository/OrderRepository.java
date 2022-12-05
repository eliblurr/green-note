package org.tlc.microservices.reportingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tlc.microservices.reportingservice.model.OrderTrade;

public interface OrderRepository extends JpaRepository<OrderTrade, Integer> {
}
