package nl.averageflow.joeswarehouse.repositories;

import nl.averageflow.joeswarehouse.models.ArticleStock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ArticleStocksRepository extends CrudRepository<ArticleStock, Long> {
    @NonNull
    Optional<ArticleStock> findById(@NonNull Long id);

    @NonNull
    Set<ArticleStock> findAll();

}