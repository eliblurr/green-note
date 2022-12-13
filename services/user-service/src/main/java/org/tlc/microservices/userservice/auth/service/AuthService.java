package org.tlc.microservices.userservice.auth.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.jwt.JwtUtils;
import org.tlc.domain.base.logging.dto.LogDTO;
import org.tlc.domain.base.logging.dto.LogEventDTO;
import org.tlc.domain.base.logging.enums.Ops;
import org.tlc.microservices.userservice.auth.dto.*;
import org.tlc.microservices.userservice.dto.admin.AdminDTO;
import org.tlc.microservices.userservice.dto.customer.CustomerDTO;
import org.tlc.domain.base.exceptions.BadCredentialsException;
import org.tlc.microservices.userservice.kafka.LogProducer;
import org.tlc.microservices.userservice.model.Admin;
import org.tlc.microservices.userservice.model.Customer;
import org.tlc.microservices.userservice.repository.AdminRepository;
import org.tlc.microservices.userservice.repository.CustomerRepository;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired private JwtUtils jwtUtils;
    @Autowired private AdminRepository adminRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private PasswordEncoder encoder;

    @Autowired private final LogProducer logProducer;
    @Autowired @Qualifier("logTopic") private NewTopic topic;

    public CustomerResponseDTO validateCustomer(LoginDTO payload){
        Customer customer = customerRepository.findOneByEmail(payload.getEmail());
        if(customer == null || !encoder.matches(payload.getPassword(), customer.getPassword())){ throw new BadCredentialsException();}
        CustomerResponseDTO responseDTO = new CustomerResponseDTO();
        responseDTO.setAdmin(CustomerDTO.convertToDTO(customer));
        responseDTO.setAccess_token(jwtUtils.generateJwtToken(customer.getEmail(), customer.getId(), false));
        responseDTO.setRefresh_token(jwtUtils.generateJwtToken(customer.getEmail(), customer.getId(), true));

        // publish to logger here
        try{ this.publishActivity(customer.getId());}
        catch (Exception e){System.out.println(e.getMessage());}

        return responseDTO;
    }

    public AdminResponseDTO validateAdmin(LoginDTO payload){
        Admin admin = adminRepository.findOneByEmail(payload.getEmail());
        if(admin == null || !encoder.matches(payload.getPassword(), admin.getPassword())){ throw new BadCredentialsException();}
        AdminResponseDTO responseDTO = new AdminResponseDTO();
        responseDTO.setAdmin(AdminDTO.convertToDTO(admin));
        responseDTO.setAccess_token(jwtUtils.generateJwtToken(admin.getEmail(), admin.getId(), false));
        responseDTO.setRefresh_token(jwtUtils.generateJwtToken(admin.getEmail(), admin.getId(), true));

        // publish to logger here
        try{ this.publishActivity(admin.getId());}
        catch (Exception e){System.out.println(e.getMessage());}

        return responseDTO;
    }

    public RefreshTokenResponseDTO refreshToken(RefreshTokenDTO payload){
        jwtUtils.validateJwtToken(payload.getRefresh_token());
        String email = jwtUtils.getEmailFromJwtToken(payload.getRefresh_token());
        RefreshTokenResponseDTO responseDTO = new RefreshTokenResponseDTO();
        responseDTO.setRefresh_token(jwtUtils.generateJwtToken(email, true));
        responseDTO.setAccess_token(jwtUtils.generateJwtToken(email, false));
        return responseDTO;
    }

    private void publishActivity(UUID user){
        logProducer.setTopic(topic);
        LogEventDTO logEventDTO = new LogEventDTO(
                "sending...", "pending",
                new LogDTO(user, String.format(Ops.LOGIN.getMessage(), new Date().toString()))
        );
        logProducer.sendMessage(logEventDTO);
    }

}
