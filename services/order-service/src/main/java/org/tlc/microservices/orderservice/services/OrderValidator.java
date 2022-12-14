package org.tlc.microservices.orderservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.order.Response;
import org.tlc.domain.base.order.dto.OrderRequestDTO;
import org.tlc.domain.base.order.dto.ValidateCustomerDTO;
import org.tlc.microservices.orderservice.dto.CancelOrderDTO;
import org.tlc.microservices.orderservice.services.validation.ClientValidationService;
import org.tlc.microservices.orderservice.services.validation.PriceValidationService;

//validator takes data from market data service and ensures that the order can be made

@Service
public class OrderValidator {
    @Autowired
    ClientValidationService clientValidationService;
    @Autowired
    PriceValidationService priceValidationService;
    @Autowired
    ModelMapper modelMapper;

    public Response validate(OrderRequestDTO order) {
        ValidateCustomerDTO customerInfo = modelMapper.map(order, ValidateCustomerDTO.class);
        Response validateCustomerResponse = clientValidationService.validateCustomer(customerInfo, order);
        if (validateCustomerResponse.isSuccess()) {
            return priceValidationService.validatePrice(order);
        }
        return validateCustomerResponse;
    }

/*    public Response validate(CancelOrderDTO cancelOrderDTO){
//        Response validateCancelResquest =
    }*/

}
