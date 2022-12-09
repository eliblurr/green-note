package org.tlc.microservices.reportingservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.domain.base.order.enums.TradeStatus;



import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Trade {

    private @Id @GeneratedValue(strategy = GenerationType.AUTO) int tradeID;
//    @ManyToOne @JoinColumn
    private int orderID;
    private TradeStatus status;
    private int quantity;
    private Side side;
    private int exchangeID;
    private double price;
    private LocalDateTime created;
    private LocalDateTime updated;
}
