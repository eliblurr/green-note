package org.tlc.microservices.loggingservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tlc.microservices.loggingservice.dto.LogDTO;
import org.tlc.microservices.loggingservice.service.LogService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin(origins = {"http://127.0.0.1:*", "http://localhost:*"})
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @GetMapping(value = {"/"})
    @ResponseStatus(HttpStatus.OK)
    List<LogDTO> read(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "created, asc") String[] sort,
            @RequestParam(required = true) UUID user // add dateRangeFilter
    ){
        return logService.read(page, size, sort, user);
    }
}
