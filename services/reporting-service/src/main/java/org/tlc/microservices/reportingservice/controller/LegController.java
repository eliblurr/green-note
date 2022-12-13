package org.tlc.microservices.reportingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tlc.microservices.reportingservice.dto.LegDTO;
import org.tlc.microservices.reportingservice.services.LegService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reports/orders")
//@CrossOrigin(origins = "http://127.0.0.1:*")
@RequiredArgsConstructor // create constructor with required arguments we need at compile time
public class LegController {

    @Autowired private final LegService legService;

    @GetMapping("/{id}/legs")
    @ResponseStatus(HttpStatus.OK)
    List<LegDTO> read(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "created, asc") String[] sort,
            @RequestParam(required = false) UUID order
    ){ return legService.read(page, size, sort, order); }

    @GetMapping(value = {"/legs/{id}", "/legs/{id}/"})
    @ResponseStatus(HttpStatus.OK)
    LegDTO readById(@PathVariable("id") UUID id){
        return legService.readById(id);
    }

    // endpoint to verify there are no active orders on exchange you want to delete
    @GetMapping(value = {"/verify-unused-exchange", "/verify-unused-exchange/"})
    @ResponseStatus(HttpStatus.OK)
    boolean exchangeHasActiveLeg(@PathVariable("id") UUID exchange){
        return legService.exchangeHasActiveLeg(exchange);
    }

}
