package nl.averageflow.joeswarehouse.products;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "created_at", nullable = false)
    private BigInteger createdAt;

    @Column(name = "updated_at", nullable = false)
    private BigInteger updatedAt;

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

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public BigInteger getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(BigInteger createdAt) {
        this.createdAt = createdAt;
    }

    public BigInteger getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(BigInteger updatedAt) {
        this.updatedAt = updatedAt;
    }

    protected Product() {
    }

    public Product(String name, Float price, BigInteger createdAt, BigInteger updatedAt) {
        this.setName(name);
        this.setPrice(price);
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
    }

    @Override
    public String toString() {
        return String.format("Product[id=%d, name=%s, price=%.2f, created_at=%d, updated_at=%d]", this.getId(),
                this.getName(), this.getPrice(), this.getCreatedAt(), this.getUpdatedAt());
    }
}