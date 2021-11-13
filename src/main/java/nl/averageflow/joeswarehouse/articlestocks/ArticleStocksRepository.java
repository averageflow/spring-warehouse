package nl.averageflow.joeswarehouse.articlestocks;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface ArticleStocksRepository extends CrudRepository<ArticleStock, Long> {
    Optional<ArticleStock> findById(Long id);

    Set<ArticleStock> findAll();
}