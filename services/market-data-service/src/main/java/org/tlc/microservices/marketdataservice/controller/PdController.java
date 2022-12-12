package org.tlc.microservices.marketdataservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.microservices.marketdataservice.dto.ExchangeProducts;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class PdController {
    @Autowired
    WebClient.Builder webClientBuilder;


    @Autowired private TickerPriceController tickerpriceController;

    @Autowired @Qualifier("redisTemplate") private RedisTemplate<String,Object> redisTemplate;

    /**
     *
     * @return web client builder
     * get market data market information
     */


    @GetMapping("/pd")
//    @CacheEvict(value = "pd",allEntries = true,key = "pd")
    @CachePut(value = "pd")
    public Map<String,Object> pd(){
        ExchangeProducts[] exchangeProducts = webClientBuilder.build()
                .get()
                .uri("https://exchange.matraining.com/pd")
                .retrieve()
                .bodyToMono(ExchangeProducts[].class)
                .block();

        Map<String,Object> opsMap = Arrays.stream(
                exchangeProducts).collect(Collectors.toMap(ExchangeProducts::getTICKER, ExchangeProducts ->ExchangeProducts));
        redisTemplate.opsForHash().putAll("pd",opsMap);
        return opsMap;
    }

    /**
     *
     * @param tickerName
     * @return
     *
     * Get pd by ticker name
     */
    @GetMapping("/pd/{tickerName}")
    @Cacheable(key = "#tickerName",value = "pd")
    public Object getTicker(@PathVariable String tickerName){
        Object getTickerPd = redisTemplate.opsForHash().get("pd",tickerName);
        return getTickerPd;
    }


    /**
     *
     * @return
     * Get all tickers
     */
    @GetMapping("/CachePd")
    @Cacheable(value = "pd[]")
    public List<Object> getCachePd(){
        return redisTemplate.opsForHash().values("pd");
    }
}
