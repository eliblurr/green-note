package org.tlc.microservices.loggingservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "logs")
@RequiredArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "message", nullable = false)
    @NonNull
    private String message;

    @Column(name = "created", updatable = false)
    @CreationTimestamp
    private Timestamp created;

}
