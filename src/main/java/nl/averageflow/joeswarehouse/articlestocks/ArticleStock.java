package nl.averageflow.joeswarehouse.articlestocks;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import nl.averageflow.joeswarehouse.articles.Article;

@Table(name = "article_stocks")
@Entity
public class ArticleStock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "stock", nullable = false)
    private BigInteger stock;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @Column(name = "updated_at", nullable = false)
    private Long updatedAt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "article_id")
    private Article article;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Article getArticle() {
        return this.article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public BigInteger getStock() {
        return this.stock;
    }

    public void setStock(BigInteger stock) {
        this.stock = stock;
    }

    public Long getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    protected ArticleStock() {
    }

    public ArticleStock(Article article, BigInteger stock, Long createdAt) {
        this.setArticle(article);
        this.setStock(stock);
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(createdAt);
    }

}
