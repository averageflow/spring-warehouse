package nl.averageflow.joeswarehouse.models;

import nl.averageflow.joeswarehouse.requests.AddArticlesRequestItem;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "articles", schema = "public")
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

    //@NonNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private ArticleStock stock;

    @NonNull
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @NonNull
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    protected Article() {
    }

    public Article(AddArticlesRequestItem rawItem) {
        this.id = Long.parseLong(rawItem.getArt_id());
        this.name = rawItem.getName();
        // this.stock = new ArticleStock(this.id, Long.valueOf(rawItem.getStock()), this.createdAt);
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public ArticleStock getStock() {
        return this.stock;
    }

}
