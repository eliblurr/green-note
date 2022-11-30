package org.tlc.microservices.loggingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logs")
//@CrossOrigin(origins = "http://127.0.0.1:8080")
@RequiredArgsConstructor // create constructor with required arguments we need at compile time
public class LogController {

//    read -> sortByDate
//    readForUser -> sortByDate

}
