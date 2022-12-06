package org.tlc.microservices.userservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tlc.microservices.userservice.model.Admin;

import java.util.UUID;

// Handle base sql dialects here see->  https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html for JPA provided methods

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {
    @Query(value = "SELECT CASE WHEN EXISTS(SELECT email FROM admin WHERE email=:email ) THEN true ELSE false END", nativeQuery = true)
    Boolean adminExists(@Param("email") String email);

    Page<Admin> findAll(Pageable pageable);

    Admin findOneByEmail(String email);

}
