package org.tlc.microservices.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tlc.microservices.userservice.dto.customer.CustomerDTO;
import org.tlc.microservices.userservice.dto.portfolio.CreatePortfolioDTO;
import org.tlc.microservices.userservice.dto.portfolio.PortfolioDTO;
import org.tlc.microservices.userservice.dto.portfolio.UpdatePortfolioDTO;
import org.tlc.microservices.userservice.service.PortfolioService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users/")
//@CrossOrigin(origins = "http://127.0.0.1:8080")
@RequiredArgsConstructor // create constructor with required arguments we need at compile time
public class PortfolioController {

    private final PortfolioService portfolioService;

    @GetMapping(value = {"/portfolios", "/portfolios/"})
    @ResponseStatus(HttpStatus.OK)
    List<PortfolioDTO> read(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "created, asc") String[] sort, // defaultValue = new ArrayList<String>("created,asc")
            @RequestParam(required = false) UUID customer
    ){
        return portfolioService.read(page, size, sort, customer);
    }

    @GetMapping(value = {"/portfolios/{id}", "/portfolios/{id}/"})
    @ResponseStatus(HttpStatus.OK)
    PortfolioDTO readById(@PathVariable("id") UUID id){
        return portfolioService.readById(id);
    }

    @DeleteMapping(value = {"/portfolios/{id}", "/portfolios/{id}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeById(@PathVariable("id") UUID id){
        portfolioService.removeById(id);
    }

    @PostMapping(value = {"/customers/{id}/portfolios", "/customers/{id}/portfolios/"})
    @ResponseStatus(HttpStatus.CREATED)
    PortfolioDTO create(
            @RequestBody CreatePortfolioDTO payload,
            @PathVariable("id") UUID id
    ){ return portfolioService.create(id, payload);}

    @PatchMapping(value = {"/portfolios/{id}", "/portfolios/{id}/"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    PortfolioDTO update(@PathVariable("id") UUID id, @RequestBody UpdatePortfolioDTO payload){
        return portfolioService.updateById(id, payload);
    }

}
