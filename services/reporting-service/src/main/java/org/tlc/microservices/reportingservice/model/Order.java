package org.tlc.microservices.reportingservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.tlc.domain.base.order.enums.OrderPosition;
import org.tlc.domain.base.order.enums.OrderStatus;
import org.tlc.domain.base.order.enums.Side;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import org.tlc.domain.base.order.enums.OrderSplit;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private UUID id;
    @Column(name = "exchange_order_id", nullable = false)  private UUID exchangeOrderId;
    @Column(name = "customer", nullable = false) private UUID customer;
    @Column(name = "product", nullable = false) private String product;
    @Column(name = "price", nullable = false) private double price;
    @Column(name = "quantity", nullable = false)  private int quantity;
    @Column(name = "portfolio", nullable = false) private UUID portfolio;
    @Column(name = "side", nullable = false)   private Side side;
    @Column(name = "position", nullable = false)    private OrderPosition position;
    @Column(name = "status", nullable = false)   private OrderStatus status;
    @Column(name = "split", nullable = false)   private OrderSplit split;
    @Column(name = "updated") @UpdateTimestamp private Timestamp updated;
    @Column(name = "created", updatable = false) @CreationTimestamp private Timestamp created;
    @OneToMany(mappedBy = "order") private List<Leg> legs;

}
