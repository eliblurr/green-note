package org.tlc.microservices.userservice.listeners;

import jakarta.annotation.PreDestroy;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.tlc.microservices.userservice.exceptions.BadOperationException;
import org.tlc.microservices.userservice.model.Portfolio;
import org.tlc.microservices.userservice.repository.PortfolioRepository;
import org.tlc.microservices.userservice.service.PortfolioService;

import java.util.Optional;

public class PortfolioListener {

    @Autowired
    PortfolioRepository portfolioRepository;

    @PreUpdate
    private void beforeAnyUpdate(Portfolio portfolio) {
        if (portfolio.getIs_default()){
            Optional<Portfolio> _portfolio = Optional.ofNullable(portfolioRepository.findOneByIsDefault(portfolio.getCustomer().getId()));
            if (!_portfolio.isPresent()){
                    throw new BadOperationException("default portfolio already exists");
            }
        }
    }

}

