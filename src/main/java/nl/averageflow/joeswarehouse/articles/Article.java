package nl.averageflow.joeswarehouse.articles;

import javax.persistence.Entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String name;

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

    protected Article() {
    }

    public Article(String name, BigInteger createdAt, BigInteger updatedAt) {
        this.setName(name);
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
    }

}
