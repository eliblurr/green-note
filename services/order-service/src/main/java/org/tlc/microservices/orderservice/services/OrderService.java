package org.tlc.microservices.orderservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.domain.base.order.dto.OrderRequestDTO;
import org.tlc.domain.base.order.dto.SaveOrderDTO;
import org.tlc.domain.base.order.enums.OrderStatus;
import org.tlc.domain.base.order.Response;
import org.tlc.microservices.orderservice.configuration.ExchangesConfig;
import org.tlc.microservices.orderservice.dto.CancelOrderDTO;
import org.tlc.microservices.orderservice.dto.SaveLegDTO;
import org.tlc.microservices.orderservice.dto.UpdateOrderDTO;
import org.tlc.microservices.orderservice.dto.UpdateOrderOnExchangeDTO;
import org.tlc.microservices.orderservice.services.processingstrategies.DefaultOrderProcessor;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    @Autowired
    private DefaultOrderProcessor orderProcessor;
    @Autowired
    private OrderValidator validator;
    @Autowired
    private PublishingService publishingService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    WebClient.Builder webClientBuilder;

    @Autowired
    private ExchangesConfig exchanges;

    @Value("${environment.apikey}")
    private String apiKey;

    public Response placeOrder(@Validated OrderRequestDTO orderRequest) {
        // validate order
        Response resp = validator.validate(orderRequest);
        if (!resp.isSuccess()) {
            SaveOrderDTO order = new SaveOrderDTO(orderRequest, OrderStatus.REJECTED);
            publishingService.saveOrder(order);
            return resp;
        }

        SaveOrderDTO order = new SaveOrderDTO(orderRequest, OrderStatus.ACCEPTED);
        SaveLegDTO trade = orderProcessor.processOrder(order);
        //will be replaced with a list of trades
        System.out.println(trade);
        publishingService.saveOrder(order);
        //orderPublisher.saveTrades(trade);
        System.out.println(order);
        return resp;
    }


    public Boolean cancelOrder(CancelOrderDTO cancelOrderDTO) throws Exception {
//        String exchangeURL = exchanges.get(cancelOrderDTO.getExchangeKey());
//        use order client validation service to get uuid
        String exchangeURL = "https://exchange.matraining.com";
        String orderId = cancelOrderDTO.getOrderId().toString();
        System.out.println(cancelOrderDTO);
        return webClientBuilder.build()
                .delete()
                .uri(exchangeURL + apiKey + "/order/" + orderId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    public Boolean updateOrder(UpdateOrderDTO updateOrderDTO) throws Exception {
        String exchangeURL = exchanges.get(updateOrderDTO.getExchangeKey());
        String orderId = updateOrderDTO.getOrderId().toString();
        System.out.println(updateOrderDTO);
        return webClientBuilder.build()
                .put()
                .uri(exchangeURL + apiKey + "/order/" + orderId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(new UpdateOrderOnExchangeDTO(updateOrderDTO)), UpdateOrderDTO.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }


}
