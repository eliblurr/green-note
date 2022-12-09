package org.tlc.microservices.userservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tlc.microservices.userservice.model.Customer;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query(value = "SELECT CASE WHEN EXISTS(SELECT email FROM customer WHERE email=:email ) THEN true ELSE false END", nativeQuery = true)
    Boolean customerExists(@Param("email") String email);

    @Query(value = "SELECT CASE WHEN EXISTS(SELECT user FROM customer WHERE id=:user ) THEN true ELSE false END", nativeQuery = true)
    Boolean customerExistsById(@Param("user") UUID user);

    Page<Customer> findAll(Pageable pageable);

    Customer findOneByEmail(String email);
}