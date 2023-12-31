package org.tlc.microservices.loggingservice.service;

import lombok.RequiredArgsConstructor;
//import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.domain.base.logging.enums.Ops;
import org.tlc.microservices.loggingservice.dto.CreateLogDTO;
import org.tlc.microservices.loggingservice.dto.LogDTO;
import org.tlc.microservices.loggingservice.repository.LogRepository;
import org.tlc.microservices.loggingservice.utils.Utils;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    public List<LogDTO> read(Integer page, Integer size, String[] sort, UUID user){
        return logRepository.findByUser(user, PageRequest.of(page,size, Sort.by(Utils.generateSortOrders(sort)))).stream().map(LogDTO::convertToDTO).toList();
    }

    @Autowired private WebClient webClient;
    @Autowired private CreateLogDTO createLogDTO;
    @Autowired private ReactiveCircuitBreaker networkConnectionBreaker;

    public void create(Ops op, Timestamp occurrence, UUID user){
        Boolean result = networkConnectionBreaker.run(
                webClient.get().uri("http://user-service/user-exists?user="+user.toString()).retrieve().bodyToMono(Boolean.class),
                throwable -> Mono.just(false)
        ).block();
         if (!result){ return; }
        createLogDTO.setUser(user);
        createLogDTO.setMessage(op, occurrence);
        logRepository.save(createLogDTO.convertToEntity());
    }

    public void create(String message, UUID user){
        Boolean result = networkConnectionBreaker.run(
                webClient.get().uri("http://user-service/user-exists?user="+user.toString()).retrieve().bodyToMono(Boolean.class),
                throwable -> Mono.just(false)
        ).block();
         if (!result){ return; }
        createLogDTO.setUser(user);
        createLogDTO.setMessage(message);
        logRepository.save(createLogDTO.convertToEntity());
    }
}
