package nl.averageflow.springwarehouse.domain.category.repository;

import nl.averageflow.springwarehouse.domain.category.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, UUID> {

    @NonNull
    Optional<Category> findByUid(@NonNull UUID uid);

    @NonNull
    Set<Category> findAll();

    @Transactional
    void deleteByUid(@NonNull UUID uid);
}