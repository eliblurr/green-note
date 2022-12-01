package org.tlc.microservices.orderservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;

@Service
public class OrderService {


    private static final ModelMapper modelMapper = new ModelMapper();

    //dto or model?
    public void placeOrder(OrderRequestDTO orderRequestDTO){

//        orderRepository.save(orderRequestDTO.toModel());
    }


//    public List<ReadOrderDTO> getAllOrders() {
//        return orderRepository.findAll().stream()
//                .map(order -> modelMapper.map(order, ReadOrderDTO.class))
//                .toList();
//    }
}
