package ca.angios.tableagricole.web.rest;

import ca.angios.tableagricole.service.TerritoireService;
import ca.angios.tableagricole.service.dto.TerritoireDTO;
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
 * REST controller for managing {@link ca.angios.tableagricole.domain.Territoire}.
 */
@RestController
@RequestMapping("/api")
public class TerritoireResource {
    private final Logger log = LoggerFactory.getLogger(TerritoireResource.class);

    private static final String ENTITY_NAME = "territoire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TerritoireService territoireService;

    public TerritoireResource(TerritoireService territoireService) {
        this.territoireService = territoireService;
    }

    /**
     * {@code POST  /territoires} : Create a new territoire.
     *
     * @param territoireDTO the territoireDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new territoireDTO, or with status {@code 400 (Bad Request)} if the territoire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/territoires")
    public ResponseEntity<TerritoireDTO> createTerritoire(@Valid @RequestBody TerritoireDTO territoireDTO) throws URISyntaxException {
        log.debug("REST request to save Territoire : {}", territoireDTO);
        if (territoireDTO.getId() != null) {
            throw new BadRequestAlertException("A new territoire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TerritoireDTO result = territoireService.save(territoireDTO);
        return ResponseEntity
            .created(new URI("/api/territoires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /territoires} : Updates an existing territoire.
     *
     * @param territoireDTO the territoireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated territoireDTO,
     * or with status {@code 400 (Bad Request)} if the territoireDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the territoireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/territoires")
    public ResponseEntity<TerritoireDTO> updateTerritoire(@Valid @RequestBody TerritoireDTO territoireDTO) throws URISyntaxException {
        log.debug("REST request to update Territoire : {}", territoireDTO);
        if (territoireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TerritoireDTO result = territoireService.save(territoireDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, territoireDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /territoires} : get all the territoires.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of territoires in body.
     */
    @GetMapping("/territoires")
    public List<TerritoireDTO> getAllTerritoires() {
        log.debug("REST request to get all Territoires");
        return territoireService.findAll();
    }

    /**
     * {@code GET  /territoires/:id} : get the "id" territoire.
     *
     * @param id the id of the territoireDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the territoireDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/territoires/{id}")
    public ResponseEntity<TerritoireDTO> getTerritoire(@PathVariable Long id) {
        log.debug("REST request to get Territoire : {}", id);
        Optional<TerritoireDTO> territoireDTO = territoireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(territoireDTO);
    }

    /**
     * {@code DELETE  /territoires/:id} : delete the "id" territoire.
     *
     * @param id the id of the territoireDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/territoires/{id}")
    public ResponseEntity<Void> deleteTerritoire(@PathVariable Long id) {
        log.debug("REST request to delete Territoire : {}", id);
        territoireService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
