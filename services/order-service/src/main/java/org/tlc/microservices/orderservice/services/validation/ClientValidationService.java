package org.tlc.microservices.orderservice.services.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.dto.ClientValidationDTO;
import org.tlc.domain.base.order.dto.OrderRequestDTO;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.domain.base.order.Response;
import org.tlc.domain.base.order.dto.ValidateCustomerDTO;

@Service
public class ClientValidationService {
    @Autowired
    ClientDataFetcher clientDataFetcher;

    public Response validateCustomer(ValidateCustomerDTO customerInfo, OrderRequestDTO order) {
        try {
//            ClientValidationDTO customer = clientDataFetcher.getUserData(customerInfo);
            ClientValidationDTO customer = new ClientValidationDTO(1000, true, true, 100, true, 10000, true, 23);
            System.out.println("\n\n"+customer.toString()+"\n\n");

            if(!customer.getUserOwnsPortfolio()){
                System.out.println("User does not own portfolio");
                return Response.INVALID_REQUEST;
            }
            if(!customer.getCanShort() && (customer.getPortfolioBalance()< order.getPrice()* order.getQuantity())){
                System.out.println("User does not own enough funds and cannot short");
                return Response.INSUFFICIENT_FUNDS;
            }

            if(!customer.getPortfolioHasProduct()){
                System.out.println("User portfolio does not contain this product");
                return Response.INVALID_REQUEST;
            }
            if(!customer.getCustomerExist()){
                System.out.println("User does not exist");
                return Response.INVALID_REQUEST;
            }

            if (order.getSide().equals(Side.SELL)) {
                int numberOfProductInInventory = customer.getProductQuantity();
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.USER_SERVICE_UNAVALABLE;
        }
    }

}
