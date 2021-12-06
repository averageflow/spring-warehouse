package nl.averageflow.joeswarehouse.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import nl.averageflow.joeswarehouse.requests.AddArticlesRequestItem;

@Table(name = "articles")
@Entity
public final class Article {
    @Id
    @SequenceGenerator(name = "articles_sequence_generator", sequenceName = "articles_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "articles_sequence_generator")
    @NonNull
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

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Long getCreatedAt() {
        return this.createdAt;
    }

    public Long getUpdatedAt() {
        return this.updatedAt;
    }

    public ArticleStock getStock() {
        return this.stock;
    }

    protected Article() {
    }

    public Article(AddArticlesRequestItem rawItem) {
        this.id = Long.parseLong(rawItem.getArt_id());
        this.name = rawItem.getName();
        ArticleStock articleStock = new ArticleStock(this.id, Long.valueOf(rawItem.getStock()), this.createdAt);
        this.stock = articleStock;
    }

}
