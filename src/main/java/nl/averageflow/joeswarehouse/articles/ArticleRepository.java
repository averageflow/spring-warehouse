package nl.averageflow.joeswarehouse.articles;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
    Optional<Article> findById(Long id);

    List<Article> findAll();
}