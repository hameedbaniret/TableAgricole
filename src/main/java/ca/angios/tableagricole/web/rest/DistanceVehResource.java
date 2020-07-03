package ca.angios.tableagricole.web.rest;

import ca.angios.tableagricole.service.DistanceVehService;
import ca.angios.tableagricole.service.dto.DistanceVehDTO;
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
 * REST controller for managing {@link ca.angios.tableagricole.domain.DistanceVeh}.
 */
@RestController
@RequestMapping("/api")
public class DistanceVehResource {
    private final Logger log = LoggerFactory.getLogger(DistanceVehResource.class);

    private static final String ENTITY_NAME = "distanceVeh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DistanceVehService distanceVehService;

    public DistanceVehResource(DistanceVehService distanceVehService) {
        this.distanceVehService = distanceVehService;
    }

    /**
     * {@code POST  /distance-vehs} : Create a new distanceVeh.
     *
     * @param distanceVehDTO the distanceVehDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new distanceVehDTO, or with status {@code 400 (Bad Request)} if the distanceVeh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/distance-vehs")
    public ResponseEntity<DistanceVehDTO> createDistanceVeh(@Valid @RequestBody DistanceVehDTO distanceVehDTO) throws URISyntaxException {
        log.debug("REST request to save DistanceVeh : {}", distanceVehDTO);
        if (distanceVehDTO.getId() != null) {
            throw new BadRequestAlertException("A new distanceVeh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DistanceVehDTO result = distanceVehService.save(distanceVehDTO);
        return ResponseEntity
            .created(new URI("/api/distance-vehs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /distance-vehs} : Updates an existing distanceVeh.
     *
     * @param distanceVehDTO the distanceVehDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated distanceVehDTO,
     * or with status {@code 400 (Bad Request)} if the distanceVehDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the distanceVehDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/distance-vehs")
    public ResponseEntity<DistanceVehDTO> updateDistanceVeh(@Valid @RequestBody DistanceVehDTO distanceVehDTO) throws URISyntaxException {
        log.debug("REST request to update DistanceVeh : {}", distanceVehDTO);
        if (distanceVehDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DistanceVehDTO result = distanceVehService.save(distanceVehDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, distanceVehDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /distance-vehs} : get all the distanceVehs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of distanceVehs in body.
     */
    @GetMapping("/distance-vehs")
    public List<DistanceVehDTO> getAllDistanceVehs() {
        log.debug("REST request to get all DistanceVehs");
        return distanceVehService.findAll();
    }

    /**
     * {@code GET  /distance-vehs/:id} : get the "id" distanceVeh.
     *
     * @param id the id of the distanceVehDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the distanceVehDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/distance-vehs/{id}")
    public ResponseEntity<DistanceVehDTO> getDistanceVeh(@PathVariable Long id) {
        log.debug("REST request to get DistanceVeh : {}", id);
        Optional<DistanceVehDTO> distanceVehDTO = distanceVehService.findOne(id);
        return ResponseUtil.wrapOrNotFound(distanceVehDTO);
    }

    /**
     * {@code DELETE  /distance-vehs/:id} : delete the "id" distanceVeh.
     *
     * @param id the id of the distanceVehDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/distance-vehs/{id}")
    public ResponseEntity<Void> deleteDistanceVeh(@PathVariable Long id) {
        log.debug("REST request to delete DistanceVeh : {}", id);
        distanceVehService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
