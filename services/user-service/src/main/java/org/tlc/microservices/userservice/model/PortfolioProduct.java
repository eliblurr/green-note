package org.tlc.microservices.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.tlc.microservices.userservice.enums.Side;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "portfolio_product")
public class PortfolioProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "ticker", nullable = false, length = 255, unique = true)
    @NonNull
    private String ticker;

    @Column(name = "quantity", nullable = false)
    @NonNull
    private Integer quantity;

    @Column(name = "updated")
    @UpdateTimestamp
    private Timestamp updated;

    @Column(name = "created", updatable = false)
    @CreationTimestamp
    private Timestamp created;

    @Enumerated(EnumType.STRING)
    private Side side;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

}
