package ca.angios.tableagricole.web.rest;

import ca.angios.tableagricole.service.TypeAlimentService;
import ca.angios.tableagricole.service.dto.TypeAlimentDTO;
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
 * REST controller for managing {@link ca.angios.tableagricole.domain.TypeAliment}.
 */
@RestController
@RequestMapping("/api")
public class TypeAlimentResource {
    private final Logger log = LoggerFactory.getLogger(TypeAlimentResource.class);

    private static final String ENTITY_NAME = "typeAliment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeAlimentService typeAlimentService;

    public TypeAlimentResource(TypeAlimentService typeAlimentService) {
        this.typeAlimentService = typeAlimentService;
    }

    /**
     * {@code POST  /type-aliments} : Create a new typeAliment.
     *
     * @param typeAlimentDTO the typeAlimentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeAlimentDTO, or with status {@code 400 (Bad Request)} if the typeAliment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-aliments")
    public ResponseEntity<TypeAlimentDTO> createTypeAliment(@Valid @RequestBody TypeAlimentDTO typeAlimentDTO) throws URISyntaxException {
        log.debug("REST request to save TypeAliment : {}", typeAlimentDTO);
        if (typeAlimentDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeAliment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeAlimentDTO result = typeAlimentService.save(typeAlimentDTO);
        return ResponseEntity
            .created(new URI("/api/type-aliments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-aliments} : Updates an existing typeAliment.
     *
     * @param typeAlimentDTO the typeAlimentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeAlimentDTO,
     * or with status {@code 400 (Bad Request)} if the typeAlimentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeAlimentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-aliments")
    public ResponseEntity<TypeAlimentDTO> updateTypeAliment(@Valid @RequestBody TypeAlimentDTO typeAlimentDTO) throws URISyntaxException {
        log.debug("REST request to update TypeAliment : {}", typeAlimentDTO);
        if (typeAlimentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeAlimentDTO result = typeAlimentService.save(typeAlimentDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeAlimentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-aliments} : get all the typeAliments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeAliments in body.
     */
    @GetMapping("/type-aliments")
    public List<TypeAlimentDTO> getAllTypeAliments() {
        log.debug("REST request to get all TypeAliments");
        return typeAlimentService.findAll();
    }

    /**
     * {@code GET  /type-aliments/:id} : get the "id" typeAliment.
     *
     * @param id the id of the typeAlimentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeAlimentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-aliments/{id}")
    public ResponseEntity<TypeAlimentDTO> getTypeAliment(@PathVariable Long id) {
        log.debug("REST request to get TypeAliment : {}", id);
        Optional<TypeAlimentDTO> typeAlimentDTO = typeAlimentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeAlimentDTO);
    }

    /**
     * {@code DELETE  /type-aliments/:id} : delete the "id" typeAliment.
     *
     * @param id the id of the typeAlimentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-aliments/{id}")
    public ResponseEntity<Void> deleteTypeAliment(@PathVariable Long id) {
        log.debug("REST request to delete TypeAliment : {}", id);
        typeAlimentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
