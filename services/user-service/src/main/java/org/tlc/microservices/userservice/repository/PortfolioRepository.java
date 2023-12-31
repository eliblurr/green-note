package org.tlc.microservices.userservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tlc.microservices.userservice.model.Portfolio;

import java.util.UUID;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, UUID> {

    Page<Portfolio> findAll(Pageable pageable);

//    @Query(value = "select * from portfolio where customer_id=:customer", nativeQuery = true)
    Page<Portfolio> findAllByCustomerId(UUID customer, Pageable pageable);

//    @Query(value = "select * from portfolio where customer_id=:customer and is_default=true", nativeQuery = true)
//    Portfolio findOneByIsDefault(@Param("customer") UUID customer);

    @Query(value = "select * from portfolio where is_default=true", nativeQuery = true)
    Boolean portfolioIsDefault(UUID portfolio);

    @Query(value = "SELECT CASE WHEN EXISTS(SELECT * FROM portfolio WHERE customer_id=:customer and id=:portfolio ) THEN true ELSE false END", nativeQuery = true)
    Boolean userOwnsPortfolio(@Param("portfolio") UUID portfolio, @Param("customer") UUID customer);

}
