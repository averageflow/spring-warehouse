package nl.averageflow.springwarehouse.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.averageflow.springwarehouse.requests.AddProductsRequestItem;
import nl.averageflow.springwarehouse.requests.ProductImagesData;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Table(name = "products")
@Entity
public final class Product {
    @Id
    @GeneratedValue
    @Column(name = "uid", nullable = false)
    private UUID uid;

    @Column(name = "item_id", nullable = false)
    private long itemId;

    @Column(name = "item_name", nullable = false)
    private String name;

    @Column(name="image_urls")
    private String imageURLs;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ArticleAmountInProduct> articleProductRelation;


    protected Product() {
    }

    public Product(AddProductsRequestItem item) {
        this.itemId = item.getItemId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.setImageURLs(item.getImageURLs());
    }

    public Product(Long itemId, String name, Double price, Iterable<String> imageURLs) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.setImageURLs(imageURLs);
    }

    public UUID getUid() {
        return this.uid;
    }

    public Long getItemId() {
        return this.itemId;
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

    public void setImageURLs(Iterable<String> imageURLs){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            this.imageURLs = objectMapper.writeValueAsString(new ProductImagesData(imageURLs));
        }catch (Exception e){
            this.imageURLs = null;
        }
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public long getProductStock() {
        ArrayList<Long> amountOfProductsPossibleList = new ArrayList<>();
        if (this.articleProductRelation == null || articleProductRelation.isEmpty()) {
            return 0L;
        }

        this.articleProductRelation.forEach(articleAmountInProduct -> {
            long articleAmountNeeded = articleAmountInProduct.getAmountOf();
            long articleStockPresent = articleAmountInProduct.getArticle().getStock();

            if (articleStockPresent >= articleAmountNeeded) {
                amountOfProductsPossibleList.add(articleStockPresent / articleAmountNeeded);
            }
        });

        if (amountOfProductsPossibleList.size() != this.articleProductRelation.size()) {
            return 0L;
        }


        Long smallestAmountPossible = amountOfProductsPossibleList.stream()
                .min(Comparator.naturalOrder())
                .get();
        return smallestAmountPossible;
    }

    public Set<ArticleAmountInProduct> getArticles() {
        return this.articleProductRelation;
    }

    public Iterable<String> getImageURLs() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ProductImagesData images  = objectMapper.readValue(this.imageURLs, ProductImagesData.class);
            return images.getUrls();
        }catch (Exception e){
            System.out.println(e);
            return List.of(new String[]{});
        }
    }
}