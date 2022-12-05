package org.tlc.microservices.reportingservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.tlc.microservices.reportingservice.dto.ReadTradeDTO;
import org.tlc.microservices.reportingservice.dto.TradeCreationDTO;
import org.tlc.microservices.reportingservice.model.Trade;
import org.tlc.microservices.reportingservice.services.TradeService;

import java.util.List;

@RestController
public class TradeController {
    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping("/trades")
    @ResponseStatus(HttpStatus.OK)
    List<ReadTradeDTO> all(){
            return tradeService.getAllTrades();
    }


}
