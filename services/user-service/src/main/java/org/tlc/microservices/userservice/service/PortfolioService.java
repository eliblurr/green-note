package org.tlc.microservices.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tlc.microservices.userservice.dto.portfolio.CreatePortfolioDTO;
import org.tlc.microservices.userservice.dto.portfolio.PortfolioDTO;
import org.tlc.microservices.userservice.dto.portfolio.UpdatePortfolioDTO;
import org.tlc.microservices.userservice.exceptions.InternalServerErrorException;
import org.tlc.microservices.userservice.exceptions.NotFoundException;
import org.tlc.microservices.userservice.model.Portfolio;
import org.tlc.microservices.userservice.repository.CustomerRepository;
import org.tlc.microservices.userservice.repository.PortfolioRepository;
import org.tlc.microservices.userservice.utils.Utils;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired private ObjectMapper objectMapper;

    public List<PortfolioDTO> read(Integer page, Integer size, String[] sort, UUID customer){
        Page<Portfolio> rs = customer == null ? portfolioRepository.findAll(
                PageRequest.of(page,size, Sort.by(Utils.generateSortOrders(sort)))
        ) : portfolioRepository.findAllByCustomerId(
                customer, PageRequest.of(page,size, Sort.by(Utils.generateSortOrders(sort)))
        );
        return rs.stream().map(PortfolioDTO::convertToDTO).toList();
    }

    public PortfolioDTO readById(UUID id){
        return PortfolioDTO.convertToDTO(
                portfolioRepository.findById(id).orElseThrow(() -> new NotFoundException(id))
        );
    }

    public void removeById(UUID id){
        portfolioRepository.deleteById(id);
    }

    public PortfolioDTO create(UUID id, CreatePortfolioDTO payload) {
        return PortfolioDTO.convertToDTO( customerRepository.findById(id).map(
                customer -> {
                    payload.setCustomer(customer);
                    return portfolioRepository.save(payload.convertToEntity());
                }
        ).orElseThrow(() -> new NotFoundException(id)) );
    }

    public PortfolioDTO updateById(UUID id, UpdatePortfolioDTO payload){
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        try{ objectMapper.readerForUpdating(portfolio).readValue(objectMapper.writeValueAsString(payload));}
        catch (JsonProcessingException e){ throw new InternalServerErrorException(e.getMessage());}

        return PortfolioDTO.convertToDTO(portfolioRepository.save(portfolio));
    }

}
