package nl.averageflow.joeswarehouse.models;

import java.math.BigInteger;
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
import javax.persistence.OneToMany;
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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ArticleAmountInProduct> articleProductRelation;

    protected Product() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Double getPrice() {
        return this.price;
    }

    public Long getCreatedAt() {
        return this.createdAt;
    }

    public Long getUpdatedAt() {
        return this.updatedAt;
    }

    public Long getStock() {
        for (ArticleAmountInProduct articleAmountInProduct : articleProductRelation) {
            Long articleAmountNeeded = articleAmountInProduct.getAmountOf();
            Long articleStockPresent = articleAmountInProduct.getArticle().getStock();

            if (articleAmountNeeded < articleStockPresent) {
                return (long) 0;
            }
        }

        return (long) 1;
    }

    public Set<ArticleAmountInProduct> getArticles() {
        // return new ArticleResponse(this.articles);
        return this.articleProductRelation;
    }

}