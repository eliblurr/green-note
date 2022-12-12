package org.tlc.microservices.reportingservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tlc.microservices.reportingservice.model.Order;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    Page<Order> findAll(Pageable pageable);

    Page<Order> findAllByCustomerId(UUID customer, Pageable pageable);

}
