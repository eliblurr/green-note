package org.tlc.microservices.userservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.Currency;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "username", nullable = false, length = 255, unique = true)
    @NonNull
    private String username;

    @Column(name = "email", nullable = false, length = 255, unique = true)
    @NonNull
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    @NonNull
    private String password; //hash password before save .i.e. override setter .. implement encryption with springboot Security

//    add formula here
    @Column(name = "balance")
    private Double balance = 0.0;

    @Column(name = "updated")
    @UpdateTimestamp
    private Timestamp updated;

    @Column(name = "created", updatable = false)
    @CreationTimestamp
    private Timestamp created;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean is_active = true;

    @Column(name = "can_short", columnDefinition = "boolean default true")
    private Boolean can_short = true;

    @OneToMany(mappedBy = "customer")
    private List<Portfolio> portfolios;

    public void setPassword(String password) {
        this.password = password; // hash password here
    }

}

