package ca.angios.tableagricole.repository;

import ca.angios.tableagricole.domain.Territoire;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Territoire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TerritoireRepository extends JpaRepository<Territoire, Long> {}
