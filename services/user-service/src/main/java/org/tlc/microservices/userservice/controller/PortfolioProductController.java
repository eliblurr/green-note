package org.tlc.microservices.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tlc.microservices.userservice.dto.product.CreatePortfolioProductDTO;
import org.tlc.microservices.userservice.dto.product.PortfolioProductDTO;
import org.tlc.microservices.userservice.dto.product.UpdatePortfolioProductDTO;
import org.tlc.microservices.userservice.service.PortfolioProductService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users/")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor // create constructor with required arguments we need at compile time
public class PortfolioProductController {

    private final PortfolioProductService portfolioProductService;

    @GetMapping(value = {"/portfolio-products","/portfolio-products/"})
    @ResponseStatus(HttpStatus.OK)
    List<PortfolioProductDTO> read(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "created, asc") String[] sort, // defaultValue = new ArrayList<String>("created,asc")
            @RequestParam(required = false) UUID portfolio
    ){
        return portfolioProductService.read(page, size, sort, portfolio);
    }

    @GetMapping(value = {"/portfolio-products/{id}", "/portfolio-products/{id}/"})
    @ResponseStatus(HttpStatus.OK)
    PortfolioProductDTO readById(@PathVariable("id") UUID id){
        return portfolioProductService.readById(id);
    }

    @DeleteMapping(value = {"/portfolio-products/{id}", "/portfolio-products/{id}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeById(@PathVariable("id") UUID id){
            portfolioProductService.removeById(id);
    }

    @PostMapping(value = {"/portfolios/{id}/products", "/portfolios/{id}/products/"})
    @ResponseStatus(HttpStatus.CREATED)
    PortfolioProductDTO create(
            @RequestBody CreatePortfolioProductDTO payload,
            @PathVariable("id") UUID id // portfolio_id
    ){ return portfolioProductService.create(id, payload);}

    @PatchMapping(value = {"/portfolio-products/{id}", "/portfolio-products/{id}/"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    PortfolioProductDTO update(@PathVariable("id") UUID id, @RequestBody UpdatePortfolioProductDTO payload){
        return portfolioProductService.updateById(id, payload);
    }

}