package nl.averageflow.joeswarehouse.models;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public final class Transaction {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    protected Transaction() {
    }

    public Long getId() {
        return this.id;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

}
