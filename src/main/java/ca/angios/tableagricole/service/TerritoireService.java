package ca.angios.tableagricole.service;

import ca.angios.tableagricole.domain.Territoire;
import ca.angios.tableagricole.repository.TerritoireRepository;
import ca.angios.tableagricole.service.dto.TerritoireDTO;
import ca.angios.tableagricole.service.mapper.TerritoireMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Territoire}.
 */
@Service
@Transactional
public class TerritoireService {
    private final Logger log = LoggerFactory.getLogger(TerritoireService.class);

    private final TerritoireRepository territoireRepository;

    private final TerritoireMapper territoireMapper;

    public TerritoireService(TerritoireRepository territoireRepository, TerritoireMapper territoireMapper) {
        this.territoireRepository = territoireRepository;
        this.territoireMapper = territoireMapper;
    }

    /**
     * Save a territoire.
     *
     * @param territoireDTO the entity to save.
     * @return the persisted entity.
     */
    public TerritoireDTO save(TerritoireDTO territoireDTO) {
        log.debug("Request to save Territoire : {}", territoireDTO);
        Territoire territoire = territoireMapper.toEntity(territoireDTO);
        territoire = territoireRepository.save(territoire);
        return territoireMapper.toDto(territoire);
    }

    /**
     * Get all the territoires.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TerritoireDTO> findAll() {
        log.debug("Request to get all Territoires");
        return territoireRepository.findAll().stream().map(territoireMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one territoire by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TerritoireDTO> findOne(Long id) {
        log.debug("Request to get Territoire : {}", id);
        return territoireRepository.findById(id).map(territoireMapper::toDto);
    }

    /**
     * Delete the territoire by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Territoire : {}", id);
        territoireRepository.deleteById(id);
    }
}
