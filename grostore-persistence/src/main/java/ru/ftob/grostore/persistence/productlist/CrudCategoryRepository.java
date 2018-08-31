package ru.ftob.grostore.persistence.productlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.ftob.grostore.model.productlist.Category;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudCategoryRepository extends JpaRepository<Category, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Category c WHERE c.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Category save(Category product);

    @Override
    Category getOne(Integer id);

    @Override
    List<Category> findAll();

    @Override
    @Transactional
    <S extends Category> List<S> saveAll(Iterable<S> entities);

    Category getByName(String name);
}
