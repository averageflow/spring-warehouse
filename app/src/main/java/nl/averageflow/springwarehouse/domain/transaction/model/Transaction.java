package nl.averageflow.springwarehouse.domain.transaction.model;

import nl.averageflow.springwarehouse.domain.user.model.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_uid", nullable = false)
    private User user;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TransactionProduct> transactionProducts;

    public Transaction() {

    }

    public Transaction(final User user) {
        this.user = user;
    }

    public UUID getUid() {
        return this.uid;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public Set<TransactionProduct> getTransactionProducts() {
        return this.transactionProducts;
    }

    public void setTransactionProducts(final Set<TransactionProduct> transactionProducts) {
        this.transactionProducts = transactionProducts;
    }

    public User getUser() {
        return this.user;
    }
}
