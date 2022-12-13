package org.tlc.microservices.orderservice.services.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.dto.ClientValidationDTO;
import org.tlc.domain.base.order.dto.OrderRequestDTO;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.microservices.orderservice.Response;
import org.tlc.microservices.orderservice.configuration.dto.ValidateCustomerDTO;

@Service
public class ClientValidationService {
    @Autowired
    ClientDataFetcher clientDataFetcher;
    public Response validateCustomer(ValidateCustomerDTO customerInfo, OrderRequestDTO order ){
        ClientValidationDTO customer = clientDataFetcher.getUserData(customerInfo);

        if (order.getSide().equals(Side.SELL)) {
            int numberOfProductInInventory = customer.getProductQuantity();// data received from inventory service
            int numberOfProductsToSell = order.getQuantity();
            if (numberOfProductInInventory < numberOfProductsToSell) {
                return Response.INVALID_QUANTITY;
            } else {
                return Response.VALID_CLIENT;
            }
        } else if (order.getSide().equals(Side.BUY)) {
            double accountBalance = customer.getPortfolioBalance();

            double orderTotal = order.getQuantity() * order.getPrice();
            if (accountBalance < orderTotal) {
                return Response.INSUFFICIENT_FUNDS;
            }
            return Response.VALID_CLIENT;

        } else {
            return Response.INVALID_REQUEST;
        }

    }

}
