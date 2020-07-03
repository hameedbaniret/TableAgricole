package ca.angios.tableagricole.service;

import ca.angios.tableagricole.domain.DistanceVelo;
import ca.angios.tableagricole.repository.DistanceVeloRepository;
import ca.angios.tableagricole.service.dto.DistanceVeloDTO;
import ca.angios.tableagricole.service.mapper.DistanceVeloMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DistanceVelo}.
 */
@Service
@Transactional
public class DistanceVeloService {
    private final Logger log = LoggerFactory.getLogger(DistanceVeloService.class);

    private final DistanceVeloRepository distanceVeloRepository;

    private final DistanceVeloMapper distanceVeloMapper;

    public DistanceVeloService(DistanceVeloRepository distanceVeloRepository, DistanceVeloMapper distanceVeloMapper) {
        this.distanceVeloRepository = distanceVeloRepository;
        this.distanceVeloMapper = distanceVeloMapper;
    }

    /**
     * Save a distanceVelo.
     *
     * @param distanceVeloDTO the entity to save.
     * @return the persisted entity.
     */
    public DistanceVeloDTO save(DistanceVeloDTO distanceVeloDTO) {
        log.debug("Request to save DistanceVelo : {}", distanceVeloDTO);
        DistanceVelo distanceVelo = distanceVeloMapper.toEntity(distanceVeloDTO);
        distanceVelo = distanceVeloRepository.save(distanceVelo);
        return distanceVeloMapper.toDto(distanceVelo);
    }

    /**
     * Get all the distanceVelos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DistanceVeloDTO> findAll() {
        log.debug("Request to get all DistanceVelos");
        return distanceVeloRepository.findAll().stream().map(distanceVeloMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one distanceVelo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DistanceVeloDTO> findOne(Long id) {
        log.debug("Request to get DistanceVelo : {}", id);
        return distanceVeloRepository.findById(id).map(distanceVeloMapper::toDto);
    }

    /**
     * Delete the distanceVelo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DistanceVelo : {}", id);
        distanceVeloRepository.deleteById(id);
    }
}
