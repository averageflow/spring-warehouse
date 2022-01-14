package nl.averageflow.springwarehouse.domain.product.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.averageflow.springwarehouse.domain.category.model.Category;
import nl.averageflow.springwarehouse.domain.product.dto.ProductImagesData;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Table(name = "products")
@Entity
public final class Product {
    public static final String[] ELEMENTS = {};

    @Id
    @GeneratedValue
    @Column(name = "uid", nullable = false)
    private UUID uid;

    @Column(name = "item_name", nullable = false)
    private String name;

    @Column(name = "image_urls")
    private String imageURLs;

    @Column(name = "price", nullable = false)
    private Double price;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_uid")
    private Category category;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ArticleAmountInProduct> articleProductRelation;


    public Product() {
    }

    public Product(final String name, final Double price, final Collection<String> imageURLs, final Category category) {
        this.name = name;
        this.price = price;
        this.setImageURLs(imageURLs);
        this.category = category;
    }

    public UUID getUid() {
        return this.uid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public long getProductStock() {
        final Collection<Long> amountOfProductsPossibleList = new ArrayList<>();
        if (this.articleProductRelation == null || this.articleProductRelation.isEmpty()) {
            return 0L;
        }

        this.articleProductRelation.forEach(articleAmountInProduct -> {
            final long articleAmountNeeded = articleAmountInProduct.getAmountOf();
            final long articleStockPresent = articleAmountInProduct.getArticle().getStock();

            if (articleStockPresent >= articleAmountNeeded) {
                amountOfProductsPossibleList.add(articleStockPresent / articleAmountNeeded);
            }
        });

        if (amountOfProductsPossibleList.size() != this.articleProductRelation.size()) {
            return 0L;
        }

        final Optional<Long> minimumAmountPossible = amountOfProductsPossibleList.stream()
                .min(Comparator.naturalOrder());

        if (minimumAmountPossible.isEmpty()) {
            return 0L;
        }

        return minimumAmountPossible.get();
    }

    public Set<ArticleAmountInProduct> getArticles() {
        return this.articleProductRelation;
    }

    public Collection<String> getImageURLs() {
        final var objectMapper = new ObjectMapper();

        try {
            final ProductImagesData images = objectMapper.readValue(this.imageURLs, ProductImagesData.class);
            return images.urls();
        } catch (final Exception e) {
            return List.of(ELEMENTS);
        }
    }

    public void setImageURLs(final Collection<String> imageURLs) {
        final var objectMapper = new ObjectMapper();

        try {
            this.imageURLs = objectMapper.writeValueAsString(new ProductImagesData(imageURLs));
        } catch (final Exception e) {
            this.imageURLs = null;
        }
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }
}