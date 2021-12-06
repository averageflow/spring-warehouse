package nl.averageflow.joeswarehouse.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Table(name = "article_stocks")
@Entity
public final class ArticleStock {
    @Id
    @SequenceGenerator(name = "article_stocks_sequence_generator", sequenceName = "article_stocks_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_stocks_sequence_generator")
    @NonNull
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "article_id")
    private Long articleId;

    @NonNull
    @Column(name = "stock", nullable = false)
    private Long stock;

    @NonNull
    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @NonNull
    @Column(name = "updated_at", nullable = false)
    private Long updatedAt;

    public Long getStock() {
        return this.stock;
    }

    public Long getArticleId() {
        return this.articleId;
    }

    protected ArticleStock() {
    }

    public ArticleStock(Long articleId, Long stock, Long createdAt) {
        this.articleId = articleId;
        this.stock = stock;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

}
