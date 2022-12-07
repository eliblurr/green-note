package org.tlc.microservices.userservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tlc.microservices.userservice.model.PortfolioProduct;
import java.util.UUID;

@Repository
public interface PortfolioProductRepository  extends JpaRepository<PortfolioProduct, UUID> {

    Page<PortfolioProduct> findAll(Pageable pageable);

//    @Query(value = "select * from portfolio_product where portfolio_id=:portfolio", nativeQuery = true)
    Page<PortfolioProduct> findAllByPortfolioId(UUID portfolio, Pageable pageable);

//    @Query(value = "", nativeQuery = true)
//    void bkUpdate(){}

    @Query(value = "select count(*) from portfolio_product where portfolio_id=:portfolio", nativeQuery = true)
    int countPortfolioProducts(@Param("portfolio") UUID portfolio);

}
