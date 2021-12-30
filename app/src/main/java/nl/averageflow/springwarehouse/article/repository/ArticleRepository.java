package nl.averageflow.springwarehouse.article.repository;

import nl.averageflow.springwarehouse.article.model.Article;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, UUID> {


    @NonNull
    Optional<Article> findByUid(@NonNull UUID uid);

    @NonNull
    Set<Article> findAll();

    @Transactional
    void deleteByUid(@NonNull UUID uid);
}