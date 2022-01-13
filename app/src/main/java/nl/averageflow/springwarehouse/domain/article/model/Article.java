package nl.averageflow.springwarehouse.domain.article.model;

import nl.averageflow.springwarehouse.domain.article.dto.AddArticlesRequestItem;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Table(name = "articles", schema = "public")
@Entity
public class Article {
    @Id
    @GeneratedValue
    @Column(name = "uid", nullable = false)
    private UUID uid;

    @Column(name = "item_name", nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "article")
    private ArticleStock stock;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    public Article() {
    }

    public Article(final AddArticlesRequestItem rawItem) {
        this.name = rawItem.name();
    }

    public UUID getUid() {
        return this.uid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
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

    public void setStock(final ArticleStock stock) {
        this.stock.setStock(stock.getStock());
    }

    public void performStockBooking(final long amountOf) {
        this.stock.setStock(this.getStock() - amountOf);
    }

}
