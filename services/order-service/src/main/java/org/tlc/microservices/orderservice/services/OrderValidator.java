package org.tlc.microservices.orderservice.services;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.domain.base.dto.ClientValidationDTO;
import org.tlc.domain.base.marketData.OrderingServiceDto;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.microservices.orderservice.Response;
import org.tlc.domain.base.order.dto.OrderRequestDTO;
import org.tlc.microservices.orderservice.configuration.dto.ProductDataDTO;
import org.tlc.microservices.orderservice.configuration.dto.ValidateCustomerDTO;
import org.tlc.microservices.orderservice.services.validation.ClientValidationService;
import org.tlc.microservices.orderservice.services.validation.PriceValidationService;

//validator takes data from market data service and ensures that the order can be made

@Service
public class OrderValidator {
    @Setter
    @Getter
    private OrderingServiceDto marketData;
    @Autowired
    WebClient.Builder webClientBuilder;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ClientValidationService clientValidationService;
    @Autowired
    PriceValidationService priceValidationService;


    public Response validate(OrderRequestDTO order) {

//        make request ot user service for inventory data
//        ValidateCustomerDTO validateCustomerDTO = modelMapper.map(order, ValidateCustomerDTO.class);
//        ClientValidationDTO customer = getUserData(validateCustomerDTO);

        ClientValidationDTO customer = new ClientValidationDTO(100.0,true,false,10_000,true,10_000,true);
        Response validateClient = clientValidationService.validateCustomer(customer,order);
        if (validateClient.isSuccess()){

            return priceValidationService.checkIfExchangeWillAcceptOrder(order);
        }
        return validateClient;

    }








}
