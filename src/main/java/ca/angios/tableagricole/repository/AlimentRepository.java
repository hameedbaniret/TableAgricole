package ca.angios.tableagricole.repository;

import ca.angios.tableagricole.domain.Aliment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Aliment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlimentRepository extends JpaRepository<Aliment, Long> {}
