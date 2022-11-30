package org.tlc.microservices.loggingservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tlc.microservices.loggingservice.model.Log;

import java.util.UUID;

@Repository
public interface LogRepository extends JpaRepository<Log, UUID> {

    Page<Log> findAll(Pageable pageable);

}
