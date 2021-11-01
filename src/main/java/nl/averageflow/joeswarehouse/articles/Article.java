package nl.averageflow.joeswarehouse.articles;

import javax.persistence.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "articles")
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String name;

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

    protected Article() {
    }

    public Article(String name, Long createdAt) {
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
