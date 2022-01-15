package nl.averageflow.springwarehouse.domain.article.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

    public Article(final String name) {
        this.name = name;
    }

    /**
     * Get the UUID of an article.
     *
     * @return the UUID of an article.
     */
    public UUID getUid() {
        return this.uid;
    }

    /**
     * Get the name of an article.
     *
     * @return the name of an article.
     */
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get the date of creation of an article.
     *
     * @return the date of creation of an article.
     */
    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Get the date of last modification of an article.
     *
     * @return the date of last modification of an article.
     */
    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    /**
     * Get the amount of stock of an article.
     *
     * @return the amount of stock of an article.
     */
    public long getStock() {
        if (this.stock == null) return 0L;

        return this.stock.getStock();
    }

    public void setStock(final ArticleStock stock) {
        if (this.stock == null) {
            this.stock = stock;
        } else if (stock == null) {
            this.stock.setStock(0L);
        } else {
            this.stock.setStock(stock.getStock());
        }
    }

    public void performStockBooking(final long amountOf) {
        this.stock.setStock(this.getStock() - amountOf);
    }

}
