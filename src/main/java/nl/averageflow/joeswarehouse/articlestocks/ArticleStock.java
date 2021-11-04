package nl.averageflow.joeswarehouse.articlestocks;

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
public class ArticleStock {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "stock", nullable = false)
    private BigInteger stock;

    @NonNull
    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @NonNull
    @Column(name = "updated_at", nullable = false)
    private Long updatedAt;

    @NonNull
    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getStock() {
        return this.stock;
    }

    public void setStock(BigInteger stock) {
        this.stock = stock;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    protected ArticleStock() {
    }

    public ArticleStock(BigInteger stock, Long createdAt) {
        this.setStock(stock);
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(createdAt);
    }

}
