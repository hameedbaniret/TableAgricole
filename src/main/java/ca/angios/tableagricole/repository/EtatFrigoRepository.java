package ca.angios.tableagricole.repository;

import ca.angios.tableagricole.domain.EtatFrigo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EtatFrigo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtatFrigoRepository extends JpaRepository<EtatFrigo, Long> {}
