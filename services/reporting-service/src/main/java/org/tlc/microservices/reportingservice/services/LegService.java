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
import org.tlc.domain.base.order.dto.CreateLegDTO;
import org.tlc.domain.base.order.enums.LegStatus;
import org.tlc.domain.base.order.enums.OrderStatus;
import org.tlc.microservices.reportingservice.dto.LegDTO;
import org.tlc.domain.base.order.dto.UpdateLegDTO;
import org.tlc.microservices.reportingservice.model.Leg;
import org.tlc.microservices.reportingservice.model.Order;
import org.tlc.microservices.reportingservice.repository.LegRepository;
import org.tlc.microservices.reportingservice.utils.Utils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LegService {

    @Autowired private LegRepository legRepository;
    private static final ModelMapper modelMapper = new ModelMapper();
    @Autowired private ObjectMapper objectMapper;

    public List<LegDTO> read(Integer page, Integer size, String[] sort, UUID order){
        Page<Leg> rs = order == null ? legRepository.findAll(
                PageRequest.of(page,size, Sort.by(Utils.generateSortOrders(sort)))
        ) : legRepository.findAllByOrder(
                order, PageRequest.of(page,size, Sort.by(Utils.generateSortOrders(sort)))
        );
        return rs.stream().map(leg -> modelMapper.map(leg, LegDTO.class)).toList();
    }

    public LegDTO readById(UUID id){
        Leg leg = legRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return modelMapper.map(leg, LegDTO.class);
    }

    // update -- used by kafka-consumer
    public void updateById(UUID id, UpdateLegDTO payload){
        Optional<Leg> leg = legRepository.findById(id);
        if (leg.isPresent()){
            try{ objectMapper.readerForUpdating(leg).readValue(objectMapper.writeValueAsString(payload));}
            catch (JsonProcessingException e){ return;}
        }
        legRepository.save(leg.get());
    }

    public void updateStatus(UUID id, LegStatus status){
        Optional<Leg> leg = legRepository.findById(id);
        if (leg.isPresent()){
            leg.get().setStatus(status);
            legRepository.save(leg.get());
        }
    }

    public Boolean legIdExist(UUID tradeId){
        return legRepository.findOneByExchangeId(tradeId);
    }

    public boolean exchangeHasActiveLeg(UUID exchange){
        return legRepository.exchangeHasActiveLeg(exchange);
    }

//    insert -- used by kafka-consumer or order-service
    public void create(CreateLegDTO createLegDTO){
        legRepository.save(modelMapper.map(createLegDTO, Leg.class));
    }

}
