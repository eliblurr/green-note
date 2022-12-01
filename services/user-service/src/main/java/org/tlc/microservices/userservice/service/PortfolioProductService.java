package org.tlc.microservices.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tlc.microservices.userservice.dto.portfolio.CreatePortfolioDTO;
import org.tlc.microservices.userservice.dto.portfolio.PortfolioDTO;
import org.tlc.microservices.userservice.dto.portfolio.UpdatePortfolioDTO;
import org.tlc.microservices.userservice.dto.product.CreatePortfolioProductDTO;
import org.tlc.microservices.userservice.dto.product.PortfolioProductDTO;
import org.tlc.microservices.userservice.dto.product.UpdatePortfolioProductDTO;
import org.tlc.microservices.userservice.exceptions.NotFoundException;
import org.tlc.microservices.userservice.model.Portfolio;
import org.tlc.microservices.userservice.model.PortfolioProduct;
import org.tlc.microservices.userservice.repository.PortfolioProductRepository;
import org.tlc.microservices.userservice.repository.PortfolioRepository;
import org.tlc.microservices.userservice.utils.Utils;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PortfolioProductService {

    @Autowired
    private PortfolioProductRepository portfolioProductRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    public PortfolioProductDTO readById(UUID id){
        return PortfolioProductDTO.convertToDTO(
                portfolioProductRepository.findById(id).orElseThrow(() -> new NotFoundException(id))
        );
    }

    public List<PortfolioProductDTO> read(Integer page, Integer size, String[] sort, UUID portfolio){
        Page<PortfolioProduct> rs = portfolio == null ? portfolioProductRepository.findAll(
                PageRequest.of(page,size, Sort.by(Utils.generateSortOrders(sort)))
        ) : portfolioProductRepository.findAllByPortfolioId(
                portfolio, PageRequest.of(page,size, Sort.by(Utils.generateSortOrders(sort)))
        );
        return rs.stream().map(PortfolioProductDTO::convertToDTO).toList();
    }

    public void removeById(UUID id){
        portfolioProductRepository.deleteById(id);
    }

    public PortfolioProductDTO create(UUID id, CreatePortfolioProductDTO payload) {
        return PortfolioProductDTO.convertToDTO( portfolioRepository.findById(id).map(
                portfolio -> {
                    payload.setPortfolio(portfolio);
                    return portfolioProductRepository.save(payload.convertToEntity());
                }
        ).orElseThrow(() -> new NotFoundException(id)) );
    }

    public PortfolioProductDTO updateById(UUID id, UpdatePortfolioProductDTO payload){
        PortfolioProduct portfolioProduct = portfolioProductRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        // do update here
        return PortfolioProductDTO.convertToDTO(portfolioProduct);
    }

}
