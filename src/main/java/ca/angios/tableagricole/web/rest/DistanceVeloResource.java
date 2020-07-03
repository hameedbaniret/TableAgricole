package ca.angios.tableagricole.web.rest;

import ca.angios.tableagricole.service.DistanceVeloService;
import ca.angios.tableagricole.service.dto.DistanceVeloDTO;
import ca.angios.tableagricole.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link ca.angios.tableagricole.domain.DistanceVelo}.
 */
@RestController
@RequestMapping("/api")
public class DistanceVeloResource {
    private final Logger log = LoggerFactory.getLogger(DistanceVeloResource.class);

    private static final String ENTITY_NAME = "distanceVelo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DistanceVeloService distanceVeloService;

    public DistanceVeloResource(DistanceVeloService distanceVeloService) {
        this.distanceVeloService = distanceVeloService;
    }

    /**
     * {@code POST  /distance-velos} : Create a new distanceVelo.
     *
     * @param distanceVeloDTO the distanceVeloDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new distanceVeloDTO, or with status {@code 400 (Bad Request)} if the distanceVelo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/distance-velos")
    public ResponseEntity<DistanceVeloDTO> createDistanceVelo(@Valid @RequestBody DistanceVeloDTO distanceVeloDTO)
        throws URISyntaxException {
        log.debug("REST request to save DistanceVelo : {}", distanceVeloDTO);
        if (distanceVeloDTO.getId() != null) {
            throw new BadRequestAlertException("A new distanceVelo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DistanceVeloDTO result = distanceVeloService.save(distanceVeloDTO);
        return ResponseEntity
            .created(new URI("/api/distance-velos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /distance-velos} : Updates an existing distanceVelo.
     *
     * @param distanceVeloDTO the distanceVeloDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated distanceVeloDTO,
     * or with status {@code 400 (Bad Request)} if the distanceVeloDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the distanceVeloDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/distance-velos")
    public ResponseEntity<DistanceVeloDTO> updateDistanceVelo(@Valid @RequestBody DistanceVeloDTO distanceVeloDTO)
        throws URISyntaxException {
        log.debug("REST request to update DistanceVelo : {}", distanceVeloDTO);
        if (distanceVeloDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DistanceVeloDTO result = distanceVeloService.save(distanceVeloDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, distanceVeloDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /distance-velos} : get all the distanceVelos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of distanceVelos in body.
     */
    @GetMapping("/distance-velos")
    public List<DistanceVeloDTO> getAllDistanceVelos() {
        log.debug("REST request to get all DistanceVelos");
        return distanceVeloService.findAll();
    }

    /**
     * {@code GET  /distance-velos/:id} : get the "id" distanceVelo.
     *
     * @param id the id of the distanceVeloDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the distanceVeloDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/distance-velos/{id}")
    public ResponseEntity<DistanceVeloDTO> getDistanceVelo(@PathVariable Long id) {
        log.debug("REST request to get DistanceVelo : {}", id);
        Optional<DistanceVeloDTO> distanceVeloDTO = distanceVeloService.findOne(id);
        return ResponseUtil.wrapOrNotFound(distanceVeloDTO);
    }

    /**
     * {@code DELETE  /distance-velos/:id} : delete the "id" distanceVelo.
     *
     * @param id the id of the distanceVeloDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/distance-velos/{id}")
    public ResponseEntity<Void> deleteDistanceVelo(@PathVariable Long id) {
        log.debug("REST request to delete DistanceVelo : {}", id);
        distanceVeloService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
