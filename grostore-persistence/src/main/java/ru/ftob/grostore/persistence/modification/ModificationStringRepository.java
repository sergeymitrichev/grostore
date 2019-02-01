package ru.ftob.grostore.persistence.modification;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.ftob.grostore.model.modification.ModificationString;

import java.util.Optional;

@Transactional(readOnly = true)
public interface ModificationStringRepository extends PagingAndSortingRepository<ModificationString, Integer> {

    @EntityGraph(attributePaths = {"categories"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM ModificationString m WHERE m.name=?1")
    Optional<ModificationString> findByName(String name);
}
