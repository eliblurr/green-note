package org.tlc.microservices.loggingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tlc.microservices.loggingservice.dto.LogDTO;
import org.tlc.microservices.loggingservice.model.Log;
import org.tlc.microservices.loggingservice.repository.LogRepository;
import org.tlc.microservices.loggingservice.utils.Utils;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    public List<LogDTO> read(Integer page, Integer size, String[] sort, UUID user){
        return logRepository.findByUser(user, PageRequest.of(page,size, Sort.by(Utils.generateSortOrders(sort)))).stream().map(LogDTO::convertToDTO).toList();
    }

}
