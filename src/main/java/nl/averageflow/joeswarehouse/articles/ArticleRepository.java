package nl.averageflow.joeswarehouse.articles;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
    Optional<Article> findById(Long id);

    Set<Article> findAll();
}