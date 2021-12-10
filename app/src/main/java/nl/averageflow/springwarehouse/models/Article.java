package nl.averageflow.springwarehouse.models;

import nl.averageflow.springwarehouse.requests.AddArticlesRequestItem;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Table(name = "articles", schema = "public")
@Entity
public final class Article {
    @Id
    @GeneratedValue
    @Column(name = "uid", nullable = false)
    private UUID uid;

    @Column(name = "item_id", nullable = false)
    private long itemId;

    @Column(name = "item_name", nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "article")
    private ArticleStock stock;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    protected Article() {
    }

    public Article(AddArticlesRequestItem rawItem) {
        this.itemId = rawItem.getItemId();
        this.name = rawItem.getName();
    }

    public UUID getUid() {
        return this.uid;
    }

    public String getName() {
        return this.name;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public long getStock() {
        return this.stock.getStock();
    }

    public void setName(String name){
        this.name = name;
    }

    public void performStockBooking(long amountOf) {
        this.stock.setStock(this.getStock() - amountOf);
    }

}
