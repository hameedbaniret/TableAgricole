package ca.angios.tableagricole.web.rest;

import ca.angios.tableagricole.service.OrganismeService;
import ca.angios.tableagricole.service.dto.OrganismeDTO;
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
 * REST controller for managing {@link ca.angios.tableagricole.domain.Organisme}.
 */
@RestController
@RequestMapping("/api")
public class OrganismeResource {
    private final Logger log = LoggerFactory.getLogger(OrganismeResource.class);

    private static final String ENTITY_NAME = "organisme";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrganismeService organismeService;

    public OrganismeResource(OrganismeService organismeService) {
        this.organismeService = organismeService;
    }

    /**
     * {@code POST  /organismes} : Create a new organisme.
     *
     * @param organismeDTO the organismeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new organismeDTO, or with status {@code 400 (Bad Request)} if the organisme has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/organismes")
    public ResponseEntity<OrganismeDTO> createOrganisme(@Valid @RequestBody OrganismeDTO organismeDTO) throws URISyntaxException {
        log.debug("REST request to save Organisme : {}", organismeDTO);
        if (organismeDTO.getId() != null) {
            throw new BadRequestAlertException("A new organisme cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrganismeDTO result = organismeService.save(organismeDTO);
        return ResponseEntity
            .created(new URI("/api/organismes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /organismes} : Updates an existing organisme.
     *
     * @param organismeDTO the organismeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated organismeDTO,
     * or with status {@code 400 (Bad Request)} if the organismeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the organismeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/organismes")
    public ResponseEntity<OrganismeDTO> updateOrganisme(@Valid @RequestBody OrganismeDTO organismeDTO) throws URISyntaxException {
        log.debug("REST request to update Organisme : {}", organismeDTO);
        if (organismeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrganismeDTO result = organismeService.save(organismeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, organismeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /organismes} : get all the organismes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of organismes in body.
     */
    @GetMapping("/organismes")
    public List<OrganismeDTO> getAllOrganismes() {
        log.debug("REST request to get all Organismes");
        return organismeService.findAll();
    }

    /**
     * {@code GET  /organismes/:id} : get the "id" organisme.
     *
     * @param id the id of the organismeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the organismeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/organismes/{id}")
    public ResponseEntity<OrganismeDTO> getOrganisme(@PathVariable Long id) {
        log.debug("REST request to get Organisme : {}", id);
        Optional<OrganismeDTO> organismeDTO = organismeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(organismeDTO);
    }

    /**
     * {@code DELETE  /organismes/:id} : delete the "id" organisme.
     *
     * @param id the id of the organismeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/organismes/{id}")
    public ResponseEntity<Void> deleteOrganisme(@PathVariable Long id) {
        log.debug("REST request to delete Organisme : {}", id);
        organismeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
