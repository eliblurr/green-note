package org.tlc.microservices.userservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.tlc.microservices.userservice.listeners.PortfolioListener;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "portfolio", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "customer_id"})
})
@EntityListeners(PortfolioListener.class)
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false, length = 255)
    @NonNull
    private String name;

    @Column(name = "is_default", columnDefinition = "boolean default false")
    @NonNull
    private Boolean is_default = false;

    @Column(name = "updated")
    @UpdateTimestamp
    private Timestamp updated;

    @Column(name = "created", updatable = false)
    @CreationTimestamp
    private Timestamp created;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean is_active = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

    @OneToMany(mappedBy = "portfolio")
    private List<PortfolioProduct> products;

    public Portfolio(String default_portfolio, boolean b, Customer customer) {
        this.name = default_portfolio;
        this.is_default = true;
        this.customer = customer;
    }
}
