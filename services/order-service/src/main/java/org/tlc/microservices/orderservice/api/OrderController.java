package org.tlc.microservices.orderservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tlc.microservices.orderservice.Config;
import org.tlc.microservices.orderservice.dto.OrderRequest;
import org.tlc.microservices.orderservice.service.OrderProcessor;
import org.tlc.microservices.orderservice.service.OrderValidator;
import org.tlc.microservices.orderservice.dto.Response;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    OrderValidator validator;

    @PostMapping("/orders")
    Response placeOrder(@RequestBody OrderRequest newOrder) {

        System.out.println(newOrder);

        // validate order
        Response resp = validator.validate(newOrder);
        if (!resp.isSuccess()) {
            throw new RuntimeException(resp.getMessage());
        }

        // place order on exchange
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        OrderProcessor processor = (OrderProcessor) ctx.getBean("processorBean");
        resp = processor.processOrder(newOrder);
        if (!resp.isSuccess()) {
            throw new RuntimeException(resp.getMessage());
        }

        // send message to listening services
        RedisTemplate<String, Object> template = (RedisTemplate<String, Object>) ctx.getBean("redisTemplate");
        template.convertAndSend("order", newOrder);

        return resp;
    }
}
