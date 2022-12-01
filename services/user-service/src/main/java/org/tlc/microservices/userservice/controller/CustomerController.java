package org.tlc.microservices.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tlc.microservices.userservice.dto.EmailDTO;
import org.tlc.microservices.userservice.dto.admin.AdminDTO;
import org.tlc.microservices.userservice.dto.admin.CreateAdminDTO;
import org.tlc.microservices.userservice.dto.customer.CreateCustomerDTO;
import org.tlc.microservices.userservice.dto.customer.CustomerDTO;
import org.tlc.microservices.userservice.service.AdminService;
import org.tlc.microservices.userservice.service.CustomerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
//@CrossOrigin(origins = "http://127.0.0.1:8080")
@RequiredArgsConstructor // create constructor with required arguments we need at compile time
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    CustomerDTO create(@RequestBody CreateCustomerDTO payload){
        return customerService.create(payload);
    }

    @PostMapping(value = {"/verify-email", "/verify-email/"})
    @ResponseStatus(HttpStatus.OK)
    Boolean customerExists(@RequestBody EmailDTO payload){
        return customerService.customerExists(payload.getEmail());
    }

    @GetMapping(value = {"/user-exists", "/user-exists/"})
    @ResponseStatus(HttpStatus.OK)
    Boolean customerExists(
            @RequestParam(required = true) UUID user
    ){
        return customerService.customerExistsById(user);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    List<CustomerDTO> read(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "created, asc") String[] sort
    ){ return customerService.read(page, size, sort);}

    @GetMapping(value = {"/{id}", "/{id}/"})
    @ResponseStatus(HttpStatus.OK)
    CustomerDTO readById(@PathVariable("id") UUID id){
        return customerService.readById(id);
    }

    @DeleteMapping(value = {"/{id}", "/{id}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeById(@PathVariable("id") UUID id){ customerService.removeById(id); }

    @PatchMapping(value = {"/{id}", "/{id}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    CustomerDTO updateById(@PathVariable("id") UUID id){
        return customerService.updateById(id);
    }

}
