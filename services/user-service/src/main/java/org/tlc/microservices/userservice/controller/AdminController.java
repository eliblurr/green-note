package org.tlc.microservices.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tlc.domain.base.logging.dto.LogDTO;
import org.tlc.domain.base.logging.dto.LogEventDTO;
import org.tlc.microservices.userservice.dto.admin.AdminDTO;
import org.tlc.microservices.userservice.dto.admin.CreateAdminDTO;
import org.tlc.microservices.userservice.dto.EmailDTO;
import org.tlc.microservices.userservice.dto.admin.UpdateAdminDTO;
import org.tlc.microservices.userservice.kafka.LogProducer;
import org.tlc.microservices.userservice.service.AdminService;
import java.util.*;

@RestController
@RequestMapping("/api/users/admins")
@RequiredArgsConstructor // create constructor with required arguments we need at compile time
public class AdminController {

    private final AdminService adminService;

    // test
    private final LogProducer logProducer;
    @Autowired @Qualifier("logTopic") private NewTopic topic;
    @PostMapping("/test")
    @ResponseStatus(HttpStatus.CREATED)
    void sdn(){

        logProducer.setTopic(topic);

        System.out.println(topic.name());
//        logProducer.
        LogEventDTO logEventDTO = new LogEventDTO(
                "sending...", "pending",
                new LogDTO(UUID.randomUUID(), "this message")
        );
        logProducer.sendMessage(logEventDTO);
    }
    // test

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    AdminDTO create(@RequestBody CreateAdminDTO payload){
        return adminService.create(payload);
    }

    @PostMapping(value = {"/verify-email", "/verify-email/"})
    @ResponseStatus(HttpStatus.OK)
    Boolean adminExists(@RequestBody EmailDTO payload){
        return adminService.adminExists(payload.getEmail());
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    List<AdminDTO> read(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "created, asc") String[] sort // defaultValue = new ArrayList<String>("created,asc")
    ){
        return adminService.read(page, size, sort);
    }

    @GetMapping(value = {"/{id}", "/{id}/"})
    @ResponseStatus(HttpStatus.OK)
    AdminDTO readById(@PathVariable("id") UUID id){
        return adminService.readById(id);
    }

    @PatchMapping(value = {"/{id}", "/{id}/"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    AdminDTO updateById(@PathVariable("id") UUID id, @RequestBody UpdateAdminDTO payload){
        return adminService.updateById(id, payload);
    }

    @DeleteMapping(value = {"/{id}", "/{id}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeById(@PathVariable("id") UUID id){
        adminService.removeById(id);
    }
}
