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
@Table(name = "portfolio")
@EntityListeners(PortfolioListener.class)
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false, length = 255, unique = true)
    @NonNull
    private String name;

    @Column(name = "is_default", columnDefinition = "boolean default true")
    @NonNull
    private Boolean is_default;

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

}
