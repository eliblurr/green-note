package org.tlc.microservices.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tlc.microservices.userservice.dto.admin.AdminDTO;
import org.tlc.microservices.userservice.dto.admin.CreateAdminDTO;
import org.tlc.microservices.userservice.dto.EmailDTO;
import org.tlc.microservices.userservice.dto.admin.UpdateAdminDTO;
import org.tlc.microservices.userservice.service.AdminService;
import java.util.*;

@RestController
@RequestMapping("/api/users/admins")
//@CrossOrigin(origins = "http://127.0.0.1:8080")
@RequiredArgsConstructor // create constructor with required arguments we need at compile time
public class AdminController {

    private final AdminService adminService;

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
