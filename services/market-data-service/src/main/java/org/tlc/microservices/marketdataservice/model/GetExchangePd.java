package org.tlc.microservices.marketdataservice.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tlc.microservices.marketdataservice.repository.CacheRepository;

@Service
public class GetExchangePd {
    @Autowired
    CacheRepository cacheRepository;

    @Cacheable(value = "cacheInfo")
    public String getPd(){
         cacheRepository.findAll();
         return "retrieved";
    }
}
