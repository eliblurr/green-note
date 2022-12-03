package org.tlc.microservices.userservice.service;

// Apply pagination and dto transforms to CRUD from repository here

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tlc.microservices.userservice.dto.admin.AdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.tlc.microservices.userservice.dto.admin.CreateAdminDTO;
import org.tlc.microservices.userservice.dto.admin.UpdateAdminDTO;
import org.tlc.microservices.userservice.exceptions.InternalServerErrorException;
import org.tlc.microservices.userservice.exceptions.NotFoundException;
import org.tlc.microservices.userservice.model.Admin;
import org.tlc.microservices.userservice.repository.AdminRepository;
import org.tlc.microservices.userservice.utils.Utils;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired private ObjectMapper objectMapper;

    public List<AdminDTO> read(Integer page, Integer size, String[] sort){
        return adminRepository.findAll(
                PageRequest.of(page,size, Sort.by(Utils.generateSortOrders(sort)))
        ).stream().map(AdminDTO::convertToDTO).toList();
    }

    public AdminDTO readById(UUID id){
        return AdminDTO.convertToDTO(
                adminRepository.findById(id).orElseThrow(() -> new NotFoundException(id))
        );
    }

    public void removeById(UUID id){
        adminRepository.deleteById(id);
    }

    public AdminDTO updateById(UUID id, UpdateAdminDTO payload) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        try{ objectMapper.readerForUpdating(admin).readValue(objectMapper.writeValueAsString(payload));}
        catch (JsonProcessingException e){ throw new InternalServerErrorException(e.getMessage());}

        return AdminDTO.convertToDTO(adminRepository.save(admin));
    }

    public AdminDTO create(CreateAdminDTO payload) {
        return AdminDTO.convertToDTO( adminRepository.save(payload.convertToEntity()) );
    }

    public Boolean adminExists(String email){
        return adminRepository.adminExists(email);
    }

}
