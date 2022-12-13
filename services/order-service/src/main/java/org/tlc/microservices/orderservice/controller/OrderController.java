package org.tlc.microservices.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tlc.domain.base.order.Response;
import org.tlc.microservices.orderservice.dto.CancelOrderDTO;
import org.tlc.domain.base.order.dto.OrderRequestDTO;
import org.tlc.microservices.orderservice.dto.UpdateOrderDTO;
import org.tlc.microservices.orderservice.services.OrderService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public Response makeOrder(@Validated @RequestBody OrderRequestDTO order) {
        System.out.println(order);
        return orderService.placeOrder(order);
    }

    @DeleteMapping("/orders")
    public Response cancelOrder(@Validated @RequestBody CancelOrderDTO cancelOrderDTO) {
        try {
            boolean isSuccess = orderService.cancelOrder(cancelOrderDTO);
            if (isSuccess) {
                return Response.ORDER_CANCELLED;
            } else {
                return Response.ORDER_NOT_CANCELLED;
            }
        } catch (Exception e) {
            return Response.INVALID_REQUEST;
        }

    }
    @PutMapping("/orders")
    public Response updateOrder(@Validated @RequestBody UpdateOrderDTO updateOrderDTO){
        try {
            boolean isSuccess = orderService.updateOrder(updateOrderDTO);
            if (isSuccess) {
                return Response.ORDER_UPDATED;
            } else {
                return Response.ORDER_NOT_UPDATED;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.INVALID_REQUEST;
        }

    }


}
