package nl.averageflow.joeswarehouse.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Table(name = "products")
@Entity
public final class Product {
    @Id
    @SequenceGenerator(name = "products_sequence_generator", sequenceName = "products_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_sequence_generator")
    @NonNull
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "item_name")
    private String name;

    @NonNull
    @Column(name = "price")
    private Double price;

    @NonNull
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @NonNull
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;


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

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public Long getProductStock() {
        List<Long> amountOfProductsPossibleList = new ArrayList<Long>();

        for (ArticleAmountInProduct articleAmountInProduct : articleProductRelation) {
            Long articleAmountNeeded = articleAmountInProduct.getAmountOf();
            Long articleStockPresent = articleAmountInProduct.getArticle().getStock().getStock();

            // System.out.printf("articleAmountNeeded: %d, articleStockPresent: %d\n",
            // articleAmountNeeded,
            // articleStockPresent);
            if (articleStockPresent < articleAmountNeeded) {
                return (long) 0;
            } else {
                amountOfProductsPossibleList.add(articleStockPresent / articleAmountNeeded);
            }
        }

        return amountOfProductsPossibleList.stream()
                .min(Comparator.naturalOrder())
                .get();
    }

    public Set<ArticleAmountInProduct> getArticles() {
        // return new ArticleResponse(this.articles);
        return this.articleProductRelation;
    }

}