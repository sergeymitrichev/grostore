package ru.ftob.grostore.persistence.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.ftob.grostore.model.product.Product;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudProductRepository extends JpaRepository<Product, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Product p WHERE p.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Product save(Product product);

    @Override
    Product getOne(Integer id);

    @Override
    List<Product> findAll();

    @Override
    @Transactional
    <S extends Product> List<S> saveAll(Iterable<S> entities);

    Product getBySku(String sku);

//    boolean existsByNameOrId()
}
