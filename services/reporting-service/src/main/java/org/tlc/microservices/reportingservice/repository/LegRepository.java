package org.tlc.microservices.reportingservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tlc.microservices.reportingservice.model.Leg;

import java.util.UUID;

@Repository
public interface LegRepository extends JpaRepository<Leg, UUID> {

    Page<Leg> findAll(Pageable pageable);

    Page<Leg> findAllByOrder(UUID order, Pageable pageable);

    @Query(value = "SELECT CASE WHEN EXISTS(SELECT * FROM legs WHERE exchange_id=:exchange and status='OPEN' ) THEN true ELSE false END", nativeQuery = true)
    boolean exchangeHasActiveLeg(@Param("exchange") UUID exchange);

}
