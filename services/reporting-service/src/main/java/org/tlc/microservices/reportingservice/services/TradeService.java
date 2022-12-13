package org.tlc.microservices.reportingservice.services;

import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.tlc.microservices.reportingservice.dto.ReadTradeDTO;
import org.tlc.microservices.reportingservice.dto.TradeCreationDTO;
import org.tlc.microservices.reportingservice.model.Trade;
import org.tlc.microservices.reportingservice.repository.TradeRepository;

import java.util.List;

@Service
public class TradeService {
    @Autowired
    private TradeRepository tradeRepository;
    private static final ModelMapper modelMapper = new ModelMapper();

    //    public List<Trade> getAllTrades(){
//            return tradeRepository.findAll();
//    }
    public List<ReadTradeDTO> getAllTrades() {
        return tradeRepository.findAll().stream()
                .map(t -> modelMapper.map(t, ReadTradeDTO.class))
                .toList();
    }


    public ReadTradeDTO getOrderId(Long Id){

    }

    public void insertNewTrade(Trade trade){
        //do some data validation
        tradeRepository.save(trade);
    }


}
