package org.tlc.microservices.userservice.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tlc.microservices.userservice.auth.dto.*;
import org.tlc.microservices.userservice.auth.service.AuthService;
import org.tlc.microservices.userservice.dto.EmailDTO;

@RestController
@RequestMapping("/api/users/auth/")
//@CrossOrigin(origins = "http://127.0.0.1:8080")
@RequiredArgsConstructor // create constructor with required arguments we need at compile time
public class AuthController {

    @Autowired private AuthService authService;

    @PostMapping(value = {"/admins", "/admins/"})
    @ResponseStatus(HttpStatus.OK)
    AdminResponseDTO authAdmin(
            @RequestBody LoginDTO payload
    ){
        return authService.validateAdmin(payload);
    }

    @PostMapping(value = {"/customers", "/customers/"})
    @ResponseStatus(HttpStatus.OK)
    CustomerResponseDTO authCustomer(
            @RequestBody LoginDTO payload
    ){
        return authService.validateCustomer(payload);
    }

    @PostMapping(value = {"/refresh-token", "/refresh-token/"})
    @ResponseStatus(HttpStatus.OK)
    RefreshTokenResponseDTO authCustomer(
            @RequestBody RefreshTokenDTO payload
    ){
        return authService.refreshToken(payload);
    }

}
