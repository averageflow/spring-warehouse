package nl.averageflow.springwarehouse.transaction.model;

import nl.averageflow.springwarehouse.product.dto.SellProductsRequest;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public final class Transaction {
    @Id
    @GeneratedValue
    @Column(name = "uid", nullable = false)
    private UUID uid;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TransactionProduct> transactionProducts;

    public Transaction() {

    }

    public Transaction(final SellProductsRequest request) {

    }

    public UUID getUid() {
        return this.uid;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public Set<TransactionProduct> getTransactionProducts() {
        return transactionProducts;
    }

    public void setTransactionProducts(final Set<TransactionProduct> transactionProducts) {
        this.transactionProducts = transactionProducts;
    }
}
