package org.tlc.microservices.userservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tlc.microservices.userservice.model.Portfolio;

import java.util.UUID;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, UUID> {

    Page<Portfolio> findAll(Pageable pageable);

//    @Query(value = "select * from portfolio where customer_id=:customer", nativeQuery = true)
    Page<Portfolio> findAllByCustomerId(UUID customer, Pageable pageable);

}
