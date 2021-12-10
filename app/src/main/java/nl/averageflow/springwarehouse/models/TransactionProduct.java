package nl.averageflow.springwarehouse.models;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name="transaction_products")
public final class TransactionProduct {
    @Id
    @NonNull
    @GeneratedValue
    @Column(name="uid")
    private UUID uid;

    @ManyToOne
    @JoinColumn(name="product_uid", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "transaction_uid", nullable = false)
    private Transaction transaction;

    @NonNull
    @Column(name="amount_of")
    private Long amountOf;

    @NonNull
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    protected TransactionProduct(){}


    @NonNull
    public UUID getUid() {
        return uid;
    }

    public Product getProduct() {
        return product;
    }

//    public Transaction getTransaction() {
//        return transaction;
//    }

    public Long getAmountOf() {
        return amountOf;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
