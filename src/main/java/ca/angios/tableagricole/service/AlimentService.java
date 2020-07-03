package ca.angios.tableagricole.service;

import ca.angios.tableagricole.domain.Aliment;
import ca.angios.tableagricole.repository.AlimentRepository;
import ca.angios.tableagricole.service.dto.AlimentDTO;
import ca.angios.tableagricole.service.mapper.AlimentMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Aliment}.
 */
@Service
@Transactional
public class AlimentService {
    private final Logger log = LoggerFactory.getLogger(AlimentService.class);

    private final AlimentRepository alimentRepository;

    private final AlimentMapper alimentMapper;

    public AlimentService(AlimentRepository alimentRepository, AlimentMapper alimentMapper) {
        this.alimentRepository = alimentRepository;
        this.alimentMapper = alimentMapper;
    }

    /**
     * Save a aliment.
     *
     * @param alimentDTO the entity to save.
     * @return the persisted entity.
     */
    public AlimentDTO save(AlimentDTO alimentDTO) {
        log.debug("Request to save Aliment : {}", alimentDTO);
        Aliment aliment = alimentMapper.toEntity(alimentDTO);
        aliment = alimentRepository.save(aliment);
        return alimentMapper.toDto(aliment);
    }

    /**
     * Get all the aliments.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AlimentDTO> findAll() {
        log.debug("Request to get all Aliments");
        return alimentRepository.findAll().stream().map(alimentMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one aliment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AlimentDTO> findOne(Long id) {
        log.debug("Request to get Aliment : {}", id);
        return alimentRepository.findById(id).map(alimentMapper::toDto);
    }

    /**
     * Delete the aliment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Aliment : {}", id);
        alimentRepository.deleteById(id);
    }
}
