package org.tlc.microservices.reportingservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tlc.microservices.reportingservice.dto.OrderCreationDTO;
import org.tlc.microservices.reportingservice.dto.ReadOrderDTO;
import org.tlc.microservices.reportingservice.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    private static final ModelMapper modelMapper = new ModelMapper();


    public List<ReadOrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> modelMapper.map(order, ReadOrderDTO.class))
                .toList();
    }

//    add pagination

    //    create user update balance
//    partial updates


//    public void insertNewOrder(Order order){
//        //do some data validation
//      /// orderRepository.save(order);
//    }
}
