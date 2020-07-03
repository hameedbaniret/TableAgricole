package ca.angios.tableagricole.service;

import ca.angios.tableagricole.domain.DistanceVeh;
import ca.angios.tableagricole.repository.DistanceVehRepository;
import ca.angios.tableagricole.service.dto.DistanceVehDTO;
import ca.angios.tableagricole.service.mapper.DistanceVehMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DistanceVeh}.
 */
@Service
@Transactional
public class DistanceVehService {
    private final Logger log = LoggerFactory.getLogger(DistanceVehService.class);

    private final DistanceVehRepository distanceVehRepository;

    private final DistanceVehMapper distanceVehMapper;

    public DistanceVehService(DistanceVehRepository distanceVehRepository, DistanceVehMapper distanceVehMapper) {
        this.distanceVehRepository = distanceVehRepository;
        this.distanceVehMapper = distanceVehMapper;
    }

    /**
     * Save a distanceVeh.
     *
     * @param distanceVehDTO the entity to save.
     * @return the persisted entity.
     */
    public DistanceVehDTO save(DistanceVehDTO distanceVehDTO) {
        log.debug("Request to save DistanceVeh : {}", distanceVehDTO);
        DistanceVeh distanceVeh = distanceVehMapper.toEntity(distanceVehDTO);
        distanceVeh = distanceVehRepository.save(distanceVeh);
        return distanceVehMapper.toDto(distanceVeh);
    }

    /**
     * Get all the distanceVehs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DistanceVehDTO> findAll() {
        log.debug("Request to get all DistanceVehs");
        return distanceVehRepository.findAll().stream().map(distanceVehMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one distanceVeh by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DistanceVehDTO> findOne(Long id) {
        log.debug("Request to get DistanceVeh : {}", id);
        return distanceVehRepository.findById(id).map(distanceVehMapper::toDto);
    }

    /**
     * Delete the distanceVeh by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DistanceVeh : {}", id);
        distanceVehRepository.deleteById(id);
    }
}
