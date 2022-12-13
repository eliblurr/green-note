package org.tlc.microservices.reportingservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tlc.microservices.reportingservice.model.Order;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    Page<Order> findAll(Pageable pageable);

    @Query(value = "select * from orders where customer=:customer", nativeQuery = true)
    Page<Order> findAllByCustomerId(@Param("customer") UUID customer, Pageable pageable);

}
