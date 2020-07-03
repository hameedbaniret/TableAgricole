package ca.angios.tableagricole.web.rest;

import ca.angios.tableagricole.service.EtatFrigoService;
import ca.angios.tableagricole.service.dto.EtatFrigoDTO;
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
 * REST controller for managing {@link ca.angios.tableagricole.domain.EtatFrigo}.
 */
@RestController
@RequestMapping("/api")
public class EtatFrigoResource {
    private final Logger log = LoggerFactory.getLogger(EtatFrigoResource.class);

    private static final String ENTITY_NAME = "etatFrigo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtatFrigoService etatFrigoService;

    public EtatFrigoResource(EtatFrigoService etatFrigoService) {
        this.etatFrigoService = etatFrigoService;
    }

    /**
     * {@code POST  /etat-frigos} : Create a new etatFrigo.
     *
     * @param etatFrigoDTO the etatFrigoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etatFrigoDTO, or with status {@code 400 (Bad Request)} if the etatFrigo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etat-frigos")
    public ResponseEntity<EtatFrigoDTO> createEtatFrigo(@Valid @RequestBody EtatFrigoDTO etatFrigoDTO) throws URISyntaxException {
        log.debug("REST request to save EtatFrigo : {}", etatFrigoDTO);
        if (etatFrigoDTO.getId() != null) {
            throw new BadRequestAlertException("A new etatFrigo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtatFrigoDTO result = etatFrigoService.save(etatFrigoDTO);
        return ResponseEntity
            .created(new URI("/api/etat-frigos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etat-frigos} : Updates an existing etatFrigo.
     *
     * @param etatFrigoDTO the etatFrigoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etatFrigoDTO,
     * or with status {@code 400 (Bad Request)} if the etatFrigoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etatFrigoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etat-frigos")
    public ResponseEntity<EtatFrigoDTO> updateEtatFrigo(@Valid @RequestBody EtatFrigoDTO etatFrigoDTO) throws URISyntaxException {
        log.debug("REST request to update EtatFrigo : {}", etatFrigoDTO);
        if (etatFrigoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtatFrigoDTO result = etatFrigoService.save(etatFrigoDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etatFrigoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etat-frigos} : get all the etatFrigos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etatFrigos in body.
     */
    @GetMapping("/etat-frigos")
    public List<EtatFrigoDTO> getAllEtatFrigos() {
        log.debug("REST request to get all EtatFrigos");
        return etatFrigoService.findAll();
    }

    /**
     * {@code GET  /etat-frigos/:id} : get the "id" etatFrigo.
     *
     * @param id the id of the etatFrigoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etatFrigoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etat-frigos/{id}")
    public ResponseEntity<EtatFrigoDTO> getEtatFrigo(@PathVariable Long id) {
        log.debug("REST request to get EtatFrigo : {}", id);
        Optional<EtatFrigoDTO> etatFrigoDTO = etatFrigoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etatFrigoDTO);
    }

    /**
     * {@code DELETE  /etat-frigos/:id} : delete the "id" etatFrigo.
     *
     * @param id the id of the etatFrigoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etat-frigos/{id}")
    public ResponseEntity<Void> deleteEtatFrigo(@PathVariable Long id) {
        log.debug("REST request to delete EtatFrigo : {}", id);
        etatFrigoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
