package org.tlc.microservices.orderservice.services.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.order.dto.OrderRequestDTO;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.domain.base.order.Response;
import org.tlc.microservices.orderservice.dto.ProductDataDTO;

@Service
public class PriceValidationService {
    @Autowired
    ProductDataFetcher productDataFetcher;

    public Response validatePrice(OrderRequestDTO order) {
        try {
//            ProductDataDTO productDataDTO = productDataFetcher.getProductData(order.getProduct());
            ProductDataDTO productDataDTO = new ProductDataDTO(
                    "GOOGL",
                    5000,
                    0.0, 150.0, 1.09, 10000, 1.0


            );

            double maxPriceShift = productDataDTO.getMAX_PRICE_SHIFT();//retrieve from market data service
            double lastTradedPrice = productDataDTO.getLAST_TRADED_PRICE(); // retrieved from market data service

            double askPrice = productDataDTO.getASK_PRICE(); // from market data service. determines the lowest price being sold at.
            //acceptable bids lastTradedPrice for buying and askPrice for selling,
            // +/-  maxPriceShift

            if (order.getSide().equals(Side.SELL)) {
                if (Math.abs(order.getPrice() - askPrice) < maxPriceShift) {
                    return Response.VALID_ORDER;
                } else {
                    return Response.UNREASONABLE_PRICE;
                }
            }

            if (order.getSide().equals(Side.BUY)) {
                if (Math.abs(order.getPrice() - lastTradedPrice) < maxPriceShift) {
                    return Response.VALID_ORDER;
                } else {
                    return Response.UNREASONABLE_PRICE;
                }
            }
            return Response.INVALID_REQUEST;

        } catch (Exception e) {
            return Response.MD_SERVICE_UNAVALABLE;
        }
    }
}
