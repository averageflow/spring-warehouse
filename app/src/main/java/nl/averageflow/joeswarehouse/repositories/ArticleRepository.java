package nl.averageflow.joeswarehouse.repositories;

import org.springframework.stereotype.Repository;

import nl.averageflow.joeswarehouse.models.Article;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
    Optional<Article> findById(Long id);

    Set<Article> findAll();
}