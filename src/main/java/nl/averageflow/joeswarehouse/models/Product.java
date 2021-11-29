package nl.averageflow.joeswarehouse.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import nl.averageflow.joeswarehouse.responses.ArticleResponse;

@Table(name = "products")
@Entity
public final class Product {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "item_name", nullable = false)
    private String name;

    @NonNull
    @Column(name = "price", nullable = false)
    private Double price;

    @NonNull
    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @NonNull
    @Column(name = "updated_at", nullable = false)
    private Long updatedAt;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_articles", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "article_id"))
    private Set<Article> articles;

    protected Product() {
    }

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

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public ArticleResponse getArticles() {
        return new ArticleResponse(this.articles);
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}