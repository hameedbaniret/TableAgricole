package ca.angios.tableagricole.service;

import ca.angios.tableagricole.domain.Organisme;
import ca.angios.tableagricole.repository.OrganismeRepository;
import ca.angios.tableagricole.service.dto.OrganismeDTO;
import ca.angios.tableagricole.service.mapper.OrganismeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Organisme}.
 */
@Service
@Transactional
public class OrganismeService {
    private final Logger log = LoggerFactory.getLogger(OrganismeService.class);

    private final OrganismeRepository organismeRepository;

    private final OrganismeMapper organismeMapper;

    public OrganismeService(OrganismeRepository organismeRepository, OrganismeMapper organismeMapper) {
        this.organismeRepository = organismeRepository;
        this.organismeMapper = organismeMapper;
    }

    /**
     * Save a organisme.
     *
     * @param organismeDTO the entity to save.
     * @return the persisted entity.
     */
    public OrganismeDTO save(OrganismeDTO organismeDTO) {
        log.debug("Request to save Organisme : {}", organismeDTO);
        Organisme organisme = organismeMapper.toEntity(organismeDTO);
        organisme = organismeRepository.save(organisme);
        return organismeMapper.toDto(organisme);
    }

    /**
     * Get all the organismes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OrganismeDTO> findAll() {
        log.debug("Request to get all Organismes");
        return organismeRepository.findAll().stream().map(organismeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one organisme by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrganismeDTO> findOne(Long id) {
        log.debug("Request to get Organisme : {}", id);
        return organismeRepository.findById(id).map(organismeMapper::toDto);
    }

    /**
     * Delete the organisme by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Organisme : {}", id);
        organismeRepository.deleteById(id);
    }
}
