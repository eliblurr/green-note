package org.tlc.microservices.orderservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.domain.base.order.enums.OrderStatus;
import org.tlc.domain.base.order.Response;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import org.tlc.microservices.orderservice.dto.SaveOrderDTO;
import org.tlc.microservices.orderservice.dto.SaveTradeDTO;
import org.tlc.microservices.orderservice.services.processingstrategies.DefaultOrderProcessor;

@Service
public class OrderService {

    @Autowired
    private DefaultOrderProcessor orderProcessor;
    @Autowired
    private OrderValidator validator;
    @Autowired
    private OrderPublisher orderPublisher;
    @Autowired
    private  ModelMapper modelMapper;
    @Autowired
    WebClient.Builder webClientBuilder;

    @Value("${environment.apikey}")
    private String apiKey;

    public Response placeOrder(@Validated OrderRequestDTO orderRequest){
        // validate order
        Response resp = validator.validate(orderRequest);
//        SaveOrderDTO order = modelMapper.map(orderRequest, SaveOrderDTO.class);
        if (!resp.isSuccess()) {
            SaveOrderDTO order = new SaveOrderDTO(orderRequest,OrderStatus.REJECTED);
            orderPublisher.saveOrder(order);
            return resp;
        }

        SaveOrderDTO order = new SaveOrderDTO(orderRequest,OrderStatus.ACCEPTED);
        SaveTradeDTO trade = orderProcessor.processOrder(order);
        //will be replaced with a list of trades
        System.out.println(trade);
        orderPublisher.saveOrder(order);
        orderPublisher.saveTrades(trade);

        System.out.println(order);
        return resp;
    }

    //    public Response checkOrderStatus(CheckOrderStatusDTO checkStatusDTO){
//        //fetch exchange link using key
//        webClientBuilder.build()
//                .get()
//                .uri("https://exchange.matraining.com/" + apiKey +"/order" + checkStatusDTO.getTradeID())
//                .retrieve().bodyToMono()
//    }

    //public void updateOrder(){}


}
