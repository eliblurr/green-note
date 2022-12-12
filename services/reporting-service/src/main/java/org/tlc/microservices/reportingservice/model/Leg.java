package org.tlc.microservices.reportingservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.domain.base.order.enums.LegStatus;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "legs")
public class Leg {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private UUID id;
    @Column(name = "tradeId", nullable = false) @NonNull private UUID tradeId;
    @Column(name = "status", nullable = false) @NonNull private LegStatus status;
    @Column(name = "quantity", nullable = false) private int quantity;
    @Column(name = "side", nullable = false) @NonNull private Side side;
    @Column(name = "exchange_id") private UUID exchangeId;
    @Column(name = "price") private double price;
    @Column(name = "updated") @UpdateTimestamp private Timestamp updated;
    @Column(name = "created", updatable = false) @CreationTimestamp private Timestamp created;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exchange_order_id", nullable = false)
    private Order order;
}
