package org.tlc.microservices.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlc.domain.base.dto.ClientValidationDTO;
import org.tlc.domain.base.dto.ValidateCustomerDTO;
import org.tlc.microservices.userservice.dto.admin.AdminDTO;
import org.tlc.microservices.userservice.dto.customer.*;
import org.tlc.microservices.userservice.dto.portfolio.PortfolioDTO;
import org.tlc.microservices.userservice.dto.product.PortfolioProductDTO;
import org.tlc.microservices.userservice.exceptions.InternalServerErrorException;
import org.tlc.microservices.userservice.exceptions.NotFoundException;
import org.tlc.microservices.userservice.model.Admin;
import org.tlc.microservices.userservice.model.Customer;
import org.tlc.microservices.userservice.model.Portfolio;
import org.tlc.microservices.userservice.model.PortfolioProduct;
import org.tlc.microservices.userservice.repository.CustomerRepository;
import org.tlc.microservices.userservice.repository.PortfolioProductRepository;
import org.tlc.microservices.userservice.repository.PortfolioRepository;
import org.tlc.microservices.userservice.utils.Utils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired private CustomerRepository customerRepository;
    @Autowired private PortfolioRepository portfolioRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private ObjectMapper objectMapper;

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
        payload.setPassword(passwordEncoder.encode(payload.getPassword()));
        Customer customer = customerRepository.save(payload.convertToEntity());
        Portfolio portfolio = portfolioRepository.save(new Portfolio("default portfolio", true, customer));
        return CustomerDTO.convertToDTO( customer );
    }

    @Transactional(readOnly = true)
    public Boolean customerExists(String email){
        return customerRepository.customerExists(email);
    }

    @Transactional(readOnly = true)
    public Boolean customerExistsById(UUID user){
        return customerRepository.customerExistsById(user);
    }

    public CustomerDTO updateById(UUID id, UpdateCustomerDTO payload){
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new NotFoundException(id));

        try{ objectMapper.readerForUpdating(customer).readValue(objectMapper.writeValueAsString(payload));}
        catch (JsonProcessingException e){ throw new InternalServerErrorException(e.getMessage());}

        return CustomerDTO.convertToDTO( customerRepository.save(customer) );
    }

    public CustomerDTO updateBalanceById(UUID id, UpdateCustomerBalanceDTO payload){
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        customer.setBalance(customer.getBalance() + payload.getBalance());
        return CustomerDTO.convertToDTO( customerRepository.save(customer) );
    }

    @Autowired
    private PortfolioProductRepository portfolioProductRepository;

    public ClientValidationDTO validateCustomer(ValidateCustomerDTO payload){

        Optional<Customer> customer = customerRepository.findById(payload.getCustomer());
        Optional<Portfolio> portfolio = portfolioRepository.findById(payload.getPortfolio());
        Optional<PortfolioProduct> portfolioProduct = portfolioProductRepository.findOneByPortfolioAndProduct(payload.getPortfolio(), payload.getProduct());

        if(customer.isPresent() || portfolio.isEmpty() || portfolioProduct.isEmpty()){ return new ClientValidationDTO();}

        ClientValidationDTO response =  new ClientValidationDTO();

        response.setCustomerExist(true);
        response.setPortfolioBalance(portfolio.get().getBalance());
        response.setCanShort(customer.get().getCan_short());
        response.setProductQuantity(portfolioProduct.get().getQuantity());
        response.setProductTotalValue(portfolioProduct.get().getQuantity()*portfolioProduct.get().getUnit_price());
        response.setPortfolioHasProduct(
                portfolioProductRepository.productInPortfolio(
                        payload.getProduct(), payload.getPortfolio()
                )
        );
        response.setUserOwnsPortfolio(
                portfolioRepository.userOwnsPortfolio(
                        payload.getPortfolio(),
                        payload.getCustomer()
                )
        );
        return response;
    }

}
