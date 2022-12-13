package org.tlc.microservices.marketdataservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@Data
@RedisHash("TickerPrice")
public class Exchange implements Serializable {

    @Id private UUID id = UUID.randomUUID();
    private String uri;

}
