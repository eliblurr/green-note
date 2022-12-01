package org.tlc.microservices.loggingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.microservices.loggingservice.dto.CreateLogDTO;
import org.tlc.microservices.loggingservice.dto.LogDTO;
import org.tlc.microservices.loggingservice.enums.Ops;
import org.tlc.microservices.loggingservice.exceptions.NotFoundException;
import org.tlc.microservices.loggingservice.model.Log;
import org.tlc.microservices.loggingservice.repository.LogRepository;
import org.tlc.microservices.loggingservice.utils.Utils;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;
    private final WebClient webClient;

    public List<LogDTO> read(Integer page, Integer size, String[] sort, UUID user){
        return logRepository.findByUser(user, PageRequest.of(page,size, Sort.by(Utils.generateSortOrders(sort)))).stream().map(LogDTO::convertToDTO).toList();
    }

    @Autowired CreateLogDTO createLogDTO;




    public void create(Ops op, Timestamp occurrence, UUID user){

        Boolean result = webClient.get().uri("http://localhost:8080/api/customers/user-exists?user="+user.toString()).retrieve().bodyToMono(Boolean.class).block();
        System.out.println("\n\n"+result+"\n\n");
//        if (!result){ throw new NotFoundException(user); }

        createLogDTO.setUser(user);
        createLogDTO.setMessage(op, occurrence);
        logRepository.save(createLogDTO.convertToEntity());
    }

}
