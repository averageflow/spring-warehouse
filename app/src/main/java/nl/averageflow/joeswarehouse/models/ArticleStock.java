package nl.averageflow.joeswarehouse.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Table(name = "article_stocks")
@Entity
public final class ArticleStock {
    @Id
    @GeneratedValue
    @NonNull
    @Column(name = "uid")
    private UUID uid;

    @NonNull
    @Column(name = "article_uid")
    private UUID articleUid;

    //@NonNull
    @Column(name = "stock", nullable = false)
    private Long stock;

    @NonNull
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @NonNull
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    protected ArticleStock() {
    }

    public ArticleStock(UUID articleUid, Long stock) {
        this.articleUid = articleUid;
        this.stock = stock;
    }

    public Long getStock() {
        return this.stock;
    }

    public UUID getArticleUid() {
        return this.articleUid;
    }

}
