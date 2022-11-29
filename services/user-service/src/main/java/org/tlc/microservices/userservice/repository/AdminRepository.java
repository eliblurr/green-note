package org.tlc.microservices.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tlc.microservices.userservice.model.Admin;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {

//import org.springframework.data.jpa.repository.Query;
//import java.util.List;
// Handle base sql dialects here
// see https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html for JPA provided methods

//    @Query("")
//    List<Admin> readByStatus(Boolean is_active);

}
