package nl.averageflow.joeswarehouse.repositories;

import nl.averageflow.joeswarehouse.models.ArticleStock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface ArticleStocksRepository extends CrudRepository<ArticleStock, UUID> {

    @NonNull
    Set<ArticleStock> findAll();

}