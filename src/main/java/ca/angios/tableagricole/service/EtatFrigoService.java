package ca.angios.tableagricole.service;

import ca.angios.tableagricole.domain.EtatFrigo;
import ca.angios.tableagricole.repository.EtatFrigoRepository;
import ca.angios.tableagricole.service.dto.EtatFrigoDTO;
import ca.angios.tableagricole.service.mapper.EtatFrigoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EtatFrigo}.
 */
@Service
@Transactional
public class EtatFrigoService {
    private final Logger log = LoggerFactory.getLogger(EtatFrigoService.class);

    private final EtatFrigoRepository etatFrigoRepository;

    private final EtatFrigoMapper etatFrigoMapper;

    public EtatFrigoService(EtatFrigoRepository etatFrigoRepository, EtatFrigoMapper etatFrigoMapper) {
        this.etatFrigoRepository = etatFrigoRepository;
        this.etatFrigoMapper = etatFrigoMapper;
    }

    /**
     * Save a etatFrigo.
     *
     * @param etatFrigoDTO the entity to save.
     * @return the persisted entity.
     */
    public EtatFrigoDTO save(EtatFrigoDTO etatFrigoDTO) {
        log.debug("Request to save EtatFrigo : {}", etatFrigoDTO);
        EtatFrigo etatFrigo = etatFrigoMapper.toEntity(etatFrigoDTO);
        etatFrigo = etatFrigoRepository.save(etatFrigo);
        return etatFrigoMapper.toDto(etatFrigo);
    }

    /**
     * Get all the etatFrigos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EtatFrigoDTO> findAll() {
        log.debug("Request to get all EtatFrigos");
        return etatFrigoRepository.findAll().stream().map(etatFrigoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one etatFrigo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EtatFrigoDTO> findOne(Long id) {
        log.debug("Request to get EtatFrigo : {}", id);
        return etatFrigoRepository.findById(id).map(etatFrigoMapper::toDto);
    }

    /**
     * Delete the etatFrigo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EtatFrigo : {}", id);
        etatFrigoRepository.deleteById(id);
    }
}
