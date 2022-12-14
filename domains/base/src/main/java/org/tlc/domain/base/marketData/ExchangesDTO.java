package org.tlc.domain.base.marketData;

import lombok.Data;


import java.io.Serializable;
import java.util.UUID;

@Data
public class ExchangesDTO implements Serializable {
    private UUID exchangeId;
    private String ExchangeName;
    private String ExchangeUrl;
}
