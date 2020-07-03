package ca.angios.tableagricole.repository;

import ca.angios.tableagricole.domain.DistanceVelo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DistanceVelo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DistanceVeloRepository extends JpaRepository<DistanceVelo, Long> {}
