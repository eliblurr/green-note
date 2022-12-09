package org.tlc.microservices.marketdataservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tlc.microservices.marketdataservice.model.CachedInfo;


@Repository
public interface CacheRepository extends CrudRepository<CachedInfo, String> {

}