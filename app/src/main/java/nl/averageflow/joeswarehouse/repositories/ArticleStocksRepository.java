package nl.averageflow.joeswarehouse.repositories;

import org.springframework.stereotype.Repository;

import nl.averageflow.joeswarehouse.models.ArticleStock;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface ArticleStocksRepository extends CrudRepository<ArticleStock, Long> {
    Optional<ArticleStock> findById(Long id);

    Set<ArticleStock> findAll();

}