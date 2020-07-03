package ca.angios.tableagricole.repository;

import ca.angios.tableagricole.domain.DistanceVeh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DistanceVeh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DistanceVehRepository extends JpaRepository<DistanceVeh, Long> {}
