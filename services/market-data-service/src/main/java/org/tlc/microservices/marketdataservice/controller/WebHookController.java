package org.tlc.microservices.marketdataservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.microservices.marketdataservice.dto.ExchangeProducts;
import org.tlc.microservices.marketdataservice.dto.ReportingServiceDto;
import org.tlc.microservices.marketdataservice.dto.TickerDto;
import org.tlc.microservices.marketdataservice.model.Ticker;
import org.tlc.microservices.marketdataservice.service.TickerService;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("api/WebHook/newOrder")
public class WebHookController {
    @Autowired
    WebClient.Builder webClientBuilder;

    @Autowired
    TickerDto tickerDto;

    @Autowired
    TickerService tickerService;

    ReportingServiceDto newData;

    @GetMapping("/GetExchangeProduct")
    public ExchangeProducts[] getExchangeProduct(){
        return webClientBuilder.build()
                .get()
                .uri("https://exchange.matraining.com/pd")
                .retrieve()
                .bodyToMono(ExchangeProducts[].class)
                .block();
    }

    @GetMapping("/GetTickerServices")
    public TickerService getTickerPrices() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse("23/09/2007");
        long time = date.getTime();
        Timestamp timestamp = new Timestamp(time);
        ReportingServiceDto reportingServiceDto1 = new ReportingServiceDto("LIMIT","GOOGL","BUY","099748BDD",5.8,899,"ma2",timestamp);
        ReportingServiceDto reportingServiceDto2 = new ReportingServiceDto("MARKET","IBM","SELL","FGH099748BDD",1.8,900,"ma2",timestamp);
        ReportingServiceDto reportingServiceDto3 = new ReportingServiceDto("LIMIT","GOOGL","BUY","099748BDD",5.8,899,"ma2",timestamp);

        tickerService.AddTickerPrices(reportingServiceDto1.getProduct(),reportingServiceDto1.getPrice());
        tickerService.AddTickerPrices(reportingServiceDto2.getProduct(),reportingServiceDto2.getPrice());
        tickerService.AddTickerPrices(reportingServiceDto3.getProduct(),reportingServiceDto2.getPrice());

        if (newData!=null){
            tickerService.AddTickerPrices(newData.getProduct(),newData.getPrice());  //throw exception to avoid null
        }

        System.out.println("Ticker Prices: "+tickerService);
        return tickerService;
    }

    @PostMapping
    public void marketData(@RequestBody ReportingServiceDto newData) throws ParseException {
        this.newData = newData;
        //create a webclient to publish message on the redis server
        webClientBuilder.baseUrl("http://localhost:8080/api/redis")
                .defaultHeader("Content-Type", "application/json").build()
                .post().uri("/publish").body(Mono.just(newData), ReportingServiceDto.class)
                .retrieve().bodyToFlux(ReportingServiceDto.class).subscribe(
                confirmation-> {System.out.println("Message Stored!");},
                error->System.out.println(error)
        );

    }
}
