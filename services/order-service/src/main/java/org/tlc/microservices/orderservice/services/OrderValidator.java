package org.tlc.microservices.orderservice.services;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.domain.base.dto.ClientValidationDTO;
import org.tlc.domain.base.marketData.OrderingServiceDto;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.microservices.orderservice.dto.CreateOrderOnExchangeDTO;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import org.tlc.domain.base.order.Response;
import org.tlc.microservices.orderservice.dto.ProductDataDTO;
import org.tlc.microservices.orderservice.dto.ValidateCustomerDTO;
import reactor.core.publisher.Mono;

//validator takes data from market data service and ensures that the order can be made

@Service
public class OrderValidator {
    private final Response VALID_ORDER = new Response(true, "order is valid");
    private final Response INVALID_QUANTITY = new Response(false, "user does not have enough of the product in their inventory");
    private final Response UNREASONABLE_PRICE = new Response(false, "Price set is unlikely to be accepted by exchange");
    private final Response INSUFFICIENT_FUNDS = new Response(false, "Not enough funds in account for this transaction");
    private final Response INVALID_REQUEST = new Response(false, "Request received was not valid");

    @Setter
    @Getter
    private OrderingServiceDto marketData;
    @Autowired
    WebClient.Builder webClientBuilder;
    @Autowired
    ModelMapper modelMapper;


    public Response validate(OrderRequestDTO order) {

//        make request ot user service for inventory data
        ValidateCustomerDTO validateCustomerDTO = modelMapper.map(order, ValidateCustomerDTO.class);
        ClientValidationDTO customer = getUserData(validateCustomerDTO);
        if (order.getSide().equals(Side.SELL)) {
            int numberOfProductInInventory = customer.getProductQuantity();// data received from inventory service
            int numberOfProductsToSell = order.getQuantity();
            if (numberOfProductInInventory < numberOfProductsToSell) {
                return INVALID_QUANTITY;
            } else {
                return checkIfExchangeWillAcceptOrder(order);
            }
        } else if (order.getSide().equals(Side.BUY)) {
//            double accountBalance = customer.getAccountBalance();
            double accountBalance = 0;

            double orderTotal = order.getQuantity() * order.getPrice();
            if (accountBalance < orderTotal) {
                return INSUFFICIENT_FUNDS;
            }
            return checkIfExchangeWillAcceptOrder(order);

        } else {
            return INVALID_REQUEST;
        }
    }

    public Response checkIfExchangeWillAcceptOrder(OrderRequestDTO order) {
        ProductDataDTO productDataDTO = getProductData(order.getProduct());
        double maxPriceShift = productDataDTO.getMAX_PRICE_SHIFT();//retrieve from market data service
        double lastTradedPrice = productDataDTO.getLAST_TRADED_PRICE(); // retrieved from market data service

        double askPrice = productDataDTO.getASK_PRICE(); // from market data service. determines the lowest price being sold at.
        //acceptable bids lastTradedPrice for buying and askPrice for selling,
        // +/-  maxPriceShift

        if (order.getSide().equals(Side.SELL)) {
            if (Math.abs(order.getPrice() - askPrice) < maxPriceShift) {
                return VALID_ORDER;
            } else {
                return UNREASONABLE_PRICE;
            }
        }

        if (order.getSide().equals(Side.BUY)) {
            if (Math.abs(order.getPrice() - lastTradedPrice) < maxPriceShift) {
                return VALID_ORDER;
            } else {
                return UNREASONABLE_PRICE;
            }
        }
        return INVALID_REQUEST;
    }

    public ProductDataDTO getProductData(String product) {
        return webClientBuilder.build()
                .get()
                .uri("https://exchange.matraining.com/pd/" + product)
                .retrieve()
                .bodyToMono(ProductDataDTO.class)
                .block();
    }

    public ClientValidationDTO getUserData(ValidateCustomerDTO customer) {
        return webClientBuilder.build()
                .get()
                .uri("https://user-service/api/users/customersvalidate-customer" + customer)
                .retrieve()
                .bodyToMono(ClientValidationDTO.class)
                .block();
    }


}
