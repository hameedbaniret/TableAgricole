package ca.angios.tableagricole.web.rest;

import ca.angios.tableagricole.service.AlimentService;
import ca.angios.tableagricole.service.dto.AlimentDTO;
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
 * REST controller for managing {@link ca.angios.tableagricole.domain.Aliment}.
 */
@RestController
@RequestMapping("/api")
public class AlimentResource {
    private final Logger log = LoggerFactory.getLogger(AlimentResource.class);

    private static final String ENTITY_NAME = "aliment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AlimentService alimentService;

    public AlimentResource(AlimentService alimentService) {
        this.alimentService = alimentService;
    }

    /**
     * {@code POST  /aliments} : Create a new aliment.
     *
     * @param alimentDTO the alimentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new alimentDTO, or with status {@code 400 (Bad Request)} if the aliment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/aliments")
    public ResponseEntity<AlimentDTO> createAliment(@Valid @RequestBody AlimentDTO alimentDTO) throws URISyntaxException {
        log.debug("REST request to save Aliment : {}", alimentDTO);
        if (alimentDTO.getId() != null) {
            throw new BadRequestAlertException("A new aliment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AlimentDTO result = alimentService.save(alimentDTO);
        return ResponseEntity
            .created(new URI("/api/aliments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /aliments} : Updates an existing aliment.
     *
     * @param alimentDTO the alimentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated alimentDTO,
     * or with status {@code 400 (Bad Request)} if the alimentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the alimentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/aliments")
    public ResponseEntity<AlimentDTO> updateAliment(@Valid @RequestBody AlimentDTO alimentDTO) throws URISyntaxException {
        log.debug("REST request to update Aliment : {}", alimentDTO);
        if (alimentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AlimentDTO result = alimentService.save(alimentDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, alimentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /aliments} : get all the aliments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of aliments in body.
     */
    @GetMapping("/aliments")
    public List<AlimentDTO> getAllAliments() {
        log.debug("REST request to get all Aliments");
        return alimentService.findAll();
    }

    /**
     * {@code GET  /aliments/:id} : get the "id" aliment.
     *
     * @param id the id of the alimentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the alimentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/aliments/{id}")
    public ResponseEntity<AlimentDTO> getAliment(@PathVariable Long id) {
        log.debug("REST request to get Aliment : {}", id);
        Optional<AlimentDTO> alimentDTO = alimentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(alimentDTO);
    }

    /**
     * {@code DELETE  /aliments/:id} : delete the "id" aliment.
     *
     * @param id the id of the alimentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/aliments/{id}")
    public ResponseEntity<Void> deleteAliment(@PathVariable Long id) {
        log.debug("REST request to delete Aliment : {}", id);
        alimentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
