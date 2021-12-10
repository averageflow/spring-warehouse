package nl.averageflow.springwarehouse.models;

import nl.averageflow.springwarehouse.requests.SellProductsRequest;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
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

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TransactionProduct> transactionProducts;

    protected Transaction() {
    }

//    public Transaction(SellProductsRequest request){
//        request.getWantedItemsForSale()
//    }

    public UUID getUid() {
        return this.uid;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public Set<TransactionProduct> getTransactionProducts() {
        return transactionProducts;
    }
}
