package org.tlc.microservices.userservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tlc.microservices.userservice.dto.product.CreatePortfolioProductDTO;
import org.tlc.microservices.userservice.dto.product.PortfolioProductDTO;
import org.tlc.microservices.userservice.dto.product.UpdatePortfolioProductDTO;
import org.tlc.microservices.userservice.exceptions.BadOperationException;
import org.tlc.microservices.userservice.exceptions.NotFoundException;
import org.tlc.microservices.userservice.model.PortfolioProduct;
import org.tlc.microservices.userservice.repository.PortfolioProductRepository;
import org.tlc.microservices.userservice.repository.PortfolioRepository;
import org.tlc.microservices.userservice.utils.Utils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PortfolioProductService {

    @Autowired
    private PortfolioProductRepository portfolioProductRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired private ObjectMapper objectMapper;

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
        ).orElseThrow(() -> new NotFoundException("portfolio with id: "+id+" not found")) );
    }

    public PortfolioProductDTO updateById(UUID id, UpdatePortfolioProductDTO payload){
        PortfolioProduct oldPortfolioProduct = portfolioProductRepository.findById(id).orElseThrow(() -> new NotFoundException("source portfolio does not exist"));

        if (payload.getQuantity() > oldPortfolioProduct.getQuantity() ){ throw new BadOperationException("attempted to move quantity you don't have");}
//        if (id.equals(payload.getTargetPortfolio())){ throw new BadOperationException("attempted to self update");}

        PortfolioProduct newPortfolioProduct = portfolioProductRepository.findById(payload.getTargetPortfolio()).orElseThrow(() -> new NotFoundException("source portfolio does not exist"));

        newPortfolioProduct.setQuantity(newPortfolioProduct.getQuantity()+ payload.getQuantity());
        oldPortfolioProduct.setQuantity(oldPortfolioProduct.getQuantity() - payload.getQuantity());

        if (oldPortfolioProduct.getQuantity() == 0){this.removeById(oldPortfolioProduct.getId()); return null;}

        portfolioProductRepository.save(newPortfolioProduct);
        return PortfolioProductDTO.convertToDTO(portfolioProductRepository.save(oldPortfolioProduct));
    }

}
