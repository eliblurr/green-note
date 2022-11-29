package org.tlc.microservices.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tlc.microservices.userservice.dto.admin.AdminDTO;
import org.tlc.microservices.userservice.dto.admin.CreateAdminDTO;
import org.tlc.microservices.userservice.dto.admin.EmailDTO;
import org.tlc.microservices.userservice.model.Admin;
import org.tlc.microservices.userservice.service.AdminService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
//@CrossOrigin(origins = "http://127.0.0.1:8080")
@RequiredArgsConstructor // create constructor with required arguments we need at compile time
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    AdminDTO create(@RequestBody CreateAdminDTO payload){
        return adminService.create(payload);
    }

    @PostMapping("/verify-email")
    @ResponseStatus(HttpStatus.OK)
    Boolean adminExists(@RequestBody EmailDTO payload){
        return adminService.adminExists(payload.getEmail());
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    List<AdminDTO> read(){
        return adminService.read();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    AdminDTO readById(@PathVariable("id") UUID id){
        return adminService.readById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    AdminDTO updateById(@PathVariable("id") UUID id){
        return adminService.updateById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeById(@PathVariable("id") UUID id){
        adminService.removeById(id);
    }
}
