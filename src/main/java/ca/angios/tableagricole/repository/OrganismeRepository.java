package ca.angios.tableagricole.repository;

import ca.angios.tableagricole.domain.Organisme;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Organisme entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrganismeRepository extends JpaRepository<Organisme, Long> {}
