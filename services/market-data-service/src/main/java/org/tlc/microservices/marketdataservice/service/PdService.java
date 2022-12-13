package org.tlc.microservices.marketdataservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.tlc.microservices.marketdataservice.dto.ExchangeProducts;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PdService {
    @Autowired
    WebClient.Builder webClientBuilder;

    @Autowired @Qualifier("redisTemplate") private RedisTemplate<String,Object> redisTemplate;

    /**
     *
     * @return web client builder
     * get market data market information
     */

    @CachePut(value = "pd")
    public Map<String,ExchangeProducts> pd(String exchangeUrl){
        List<ExchangeProducts> exchangeProducts = Arrays.stream(Objects.requireNonNull(webClientBuilder.build()
                .get()
                .uri(exchangeUrl+"/pd")
                .retrieve()
                .bodyToMono(ExchangeProducts[].class)
                .block())).toList();

        Map<String,ExchangeProducts> opsMap =
                exchangeProducts.stream()
                .collect(Collectors.toMap(ExchangeProducts::getTicker, products ->products));

        System.out.println(opsMap);
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
    @Cacheable(value = "pd[]")
    public List<Object> getCachePd(){
        return redisTemplate.opsForHash().values("pd");
    }
}
