package nl.averageflow.joeswarehouse.articles;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import nl.averageflow.joeswarehouse.articlestocks.ArticleStock;

@Table(name = "articles")
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private ArticleStock stock;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @Column(name = "updated_at", nullable = false)
    private Long updatedAt;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setStock(ArticleStock stock) {
        this.stock = stock;
    }

    public ArticleStock getStock() {
        return this.stock;
    }

    protected Article() {
    }

    public Article(String name, ArticleStock stock, Long createdAt) {
        this.setStock(stock);
        this.setName(name);
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(createdAt);
    }

    @Override
    public String toString() {
        return String.format("Article[id=%d, name=%s, created_at=%d, updated_at=%d]", this.getId(), this.getName(),
                this.getCreatedAt(), this.getUpdatedAt());
    }

}
