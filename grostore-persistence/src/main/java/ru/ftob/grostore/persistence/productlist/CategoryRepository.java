package ru.ftob.grostore.persistence.productlist;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import ru.ftob.grostore.model.productlist.Category;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {

    @NonNull
    @Override
    @EntityGraph(value = "Category.detail", type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT c FROM Category c WHERE c.id=?1")
    Optional<Category> findById(@NonNull Integer id);

    Optional<Category> findByName(String name);

    List<Category> findAllByParent(Category category);
}
