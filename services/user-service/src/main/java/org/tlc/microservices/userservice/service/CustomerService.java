package org.tlc.microservices.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tlc.microservices.userservice.dto.admin.AdminDTO;
import org.tlc.microservices.userservice.dto.customer.CreateCustomerDTO;
import org.tlc.microservices.userservice.dto.customer.CustomerDTO;
import org.tlc.microservices.userservice.exceptions.NotFoundException;
import org.tlc.microservices.userservice.repository.CustomerRepository;
import org.tlc.microservices.userservice.utils.Utils;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDTO> read(Integer page, Integer size, String[] sort){
        return customerRepository.findAll(
                PageRequest.of(page,size, Sort.by(Utils.generateSortOrders(sort)))
        ).stream().map(CustomerDTO::convertToDTO).toList();
    }

    public CustomerDTO readById(UUID id){
        return CustomerDTO.convertToDTO(
                customerRepository.findById(id).orElseThrow(() -> new NotFoundException(id))
        );
    }

    public void removeById(UUID id){
        customerRepository.deleteById(id);
    }


    public CustomerDTO create(CreateCustomerDTO payload) {
        return CustomerDTO.convertToDTO( customerRepository.save(payload.convertToEntity()) );
    }

    public Boolean customerExists(String email){
        return customerRepository.customerExists(email);
    }

    public CustomerDTO updateById(UUID id){
//        write update method here
        return CustomerDTO.convertToDTO( customerRepository.getReferenceById(id) );
    }

}
