package nl.averageflow.joeswarehouse.repositories;

import nl.averageflow.joeswarehouse.models.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
    @NonNull
    Optional<Article> findById(@NonNull Long id);

    @NonNull
    Set<Article> findAll();
}