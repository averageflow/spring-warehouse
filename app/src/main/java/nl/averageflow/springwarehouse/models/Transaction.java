package nl.averageflow.springwarehouse.models;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public final class Transaction {
    @Id
    @NonNull
    @GeneratedValue
    @Column(name = "uid")
    private UUID uid;

    @NonNull
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    protected Transaction() {
    }

    public UUID getUid() {
        return this.uid;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

}
