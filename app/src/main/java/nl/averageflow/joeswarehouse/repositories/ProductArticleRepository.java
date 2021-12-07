package nl.averageflow.joeswarehouse.repositories;

import nl.averageflow.joeswarehouse.models.ArticleAmountInProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductArticleRepository extends CrudRepository<ArticleAmountInProduct, Long> {

    @NonNull
    Set<ArticleAmountInProduct> findAll();
}
