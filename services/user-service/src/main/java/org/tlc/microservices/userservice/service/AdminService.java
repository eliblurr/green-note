package org.tlc.microservices.userservice.service;

// Apply pagination and dto transforms to CRUD from repository here

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.tlc.microservices.userservice.dto.admin.AdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.tlc.microservices.userservice.dto.admin.CreateAdminDTO;
import org.tlc.microservices.userservice.exceptions.NotFoundException;
import org.tlc.microservices.userservice.model.Admin;
import org.tlc.microservices.userservice.repository.AdminRepository;
import org.tlc.microservices.userservice.utils.Utils;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<AdminDTO> read(Integer page, Integer size, String[] sort){

        Pageable pageable = PageRequest.of(page,size, Sort.by(Utils.generateSortOrders(sort)));
//                sort != null && !sort.isEmpty() ? PageRequest.of(page,size,
//                Sort.by(Utils.generateSortOrders(sort))
//        ) : PageRequest.of(page,size);

        return adminRepository.findAll(pageable).stream().map(
                AdminDTO::convertToDTO
        ).toList();
    }

    public AdminDTO readById(UUID id){
        return AdminDTO.convertToDTO(
                adminRepository.findById(id).orElseThrow(() -> new NotFoundException(id))
        );
    }

    public void removeById(UUID id){
        adminRepository.deleteById(id);
    }

    public AdminDTO updateById(UUID id){
//        write update method here
        return AdminDTO.convertToDTO(
                adminRepository.getReferenceById(id)
        );
    }

    public AdminDTO create(CreateAdminDTO payload) {
        return AdminDTO.convertToDTO( adminRepository.save(payload.convertToEntity()) );
    }

    public Boolean adminExists(String email){
        return adminRepository.adminExists(email);
    }

}
