package ca.angios.tableagricole.repository;

import ca.angios.tableagricole.domain.Besoin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Besoin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BesoinRepository extends JpaRepository<Besoin, Long> {}
