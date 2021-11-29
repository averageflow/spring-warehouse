package nl.averageflow.joeswarehouse.models;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Table(name = "article_stocks")
@Entity
public final class ArticleStock {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

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

    protected ArticleStock() {
    }

    public ArticleStock(Long stock, Long createdAt) {
        this.stock = stock;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

}
