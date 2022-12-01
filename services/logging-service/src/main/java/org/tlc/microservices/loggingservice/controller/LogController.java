package org.tlc.microservices.loggingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tlc.microservices.loggingservice.dto.LogDTO;
import org.tlc.microservices.loggingservice.service.LogService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/logs")
//@CrossOrigin(origins = "http://127.0.0.1:8080")
@RequiredArgsConstructor // create constructor with required arguments we need at compile time
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
