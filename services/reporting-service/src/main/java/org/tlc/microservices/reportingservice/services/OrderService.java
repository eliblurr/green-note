package org.tlc.microservices.reportingservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tlc.domain.base.exceptions.NotFoundException;
import org.tlc.domain.base.order.dto.CreateOrderDTO;
import org.tlc.domain.base.order.enums.OrderStatus;
import org.tlc.microservices.reportingservice.dto.OrderDTO;
import org.tlc.domain.base.order.dto.UpdateLegDTO;
import org.tlc.microservices.reportingservice.model.Order;
import org.tlc.microservices.reportingservice.repository.OrderRepository;
import org.tlc.microservices.reportingservice.utils.Utils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepository;
    @Autowired private ObjectMapper objectMapper;

    private static final ModelMapper modelMapper = new ModelMapper();

    public List<OrderDTO> read(Integer page, Integer size, String[] sort, UUID customer){

        Page<Order> rs = customer == null ? orderRepository.findAll(
                PageRequest.of(page,size, Sort.by(Utils.generateSortOrders(sort)))
        ) : orderRepository.findAllByCustomerId(
                customer , PageRequest.of(page,size, Sort.by(Utils.generateSortOrders(sort)))
        );

        return rs.stream().map(order -> modelMapper.map(order, OrderDTO.class)).toList();
    }

    public OrderDTO readById(UUID id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return modelMapper.map(order , OrderDTO.class);
    }

    // used by kafka-consumer
    public void updateById(UUID id, UpdateLegDTO payload){
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()){
            try{ objectMapper.readerForUpdating(order).readValue(objectMapper.writeValueAsString(payload));}
            catch (JsonProcessingException e){return;}
        }
        orderRepository.save(order.get());
    }

    public void updateStatus(UUID id, OrderStatus status){
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()){
            order.get().setStatus(status);
            orderRepository.save(order.get());
        }
    }

//    create order and its legs -- call legService to create legs -- used by kafka-consumer
    public void create(CreateOrderDTO createOrderDTO){
//        create order legs
        orderRepository.save(modelMapper.map(createOrderDTO, Order.class));
    }

}
