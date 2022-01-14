package nl.averageflow.springwarehouse.domain.transaction.model;

import nl.averageflow.springwarehouse.domain.product.model.Product;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "transaction_products")
public final class TransactionProduct {
    @ManyToOne
    @JoinColumn(name = "product_uid", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "transaction_uid", nullable = false)
    private Transaction transaction;

    @Column(name = "amount_of", nullable = false)
    private Long amountOf;

    @Id
    @GeneratedValue
    @Column(name = "uid", nullable = false)
    private UUID uid;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    public TransactionProduct() {
    }

    public TransactionProduct(final Transaction transaction, final Product product, final long amountOf) {
        this.transaction = transaction;
        this.product = product;
        this.amountOf = amountOf;
    }

    @NonNull
    public UUID getUid() {
        return this.uid;
    }

    public Product getProduct() {
        return this.product;
    }

    public Long getAmountOf() {
        return this.amountOf;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }
}
