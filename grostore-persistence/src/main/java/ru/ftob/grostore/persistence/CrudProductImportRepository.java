package ru.ftob.grostore.persistence;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ftob.grostore.model.product.ProductImport;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudProductImportRepository extends JpaRepository<ProductImport, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM ProductImport pi WHERE pi.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    ProductImport save(ProductImport productImport);

    @Override
    ProductImport getOne(Integer id);

    @Override
    List<ProductImport> findAll();

    @EntityGraph(attributePaths = {"products"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT pi FROM ProductImport  pi WHERE pi.id =?1")
    ProductImport getWithProducts(int id);
}
