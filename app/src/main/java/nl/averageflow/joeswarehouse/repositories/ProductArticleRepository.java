package nl.averageflow.joeswarehouse.repositories;

import nl.averageflow.joeswarehouse.models.ArticleAmountInProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ProductArticleRepository extends CrudRepository<ArticleAmountInProduct, Long> {

    @NonNull
    Optional<ArticleAmountInProduct> findByUid(@NonNull UUID uid);

    @NonNull
    Set<ArticleAmountInProduct> findAll();
}
