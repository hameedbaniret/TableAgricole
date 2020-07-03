package ca.angios.tableagricole.repository;

import ca.angios.tableagricole.domain.TypeAliment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeAliment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeAlimentRepository extends JpaRepository<TypeAliment, Long> {}
