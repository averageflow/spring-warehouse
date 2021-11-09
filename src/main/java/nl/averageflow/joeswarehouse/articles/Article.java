package nl.averageflow.joeswarehouse.articles;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.math.BigInteger;
import java.util.HashSet;
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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import nl.averageflow.joeswarehouse.articlestocks.ArticleStock;
import nl.averageflow.joeswarehouse.products.Product;

@Table(name = "articles")
@Entity
public class Article {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "item_name", nullable = false)
    private String name;

    @NonNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private ArticleStock stock;

    @NonNull
    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @NonNull
    @Column(name = "updated_at", nullable = false)
    private Long updatedAt;

    @ManyToMany(mappedBy = "articles", cascade = CascadeType.ALL)
    private Set<Product> products;

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

    public BigInteger getStock() {
        return this.stock.getStock();
    }

    protected Article() {
    }

}
