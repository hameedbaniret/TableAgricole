package ca.angios.tableagricole.service;

import ca.angios.tableagricole.domain.Besoin;
import ca.angios.tableagricole.repository.BesoinRepository;
import ca.angios.tableagricole.service.dto.BesoinDTO;
import ca.angios.tableagricole.service.mapper.BesoinMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Besoin}.
 */
@Service
@Transactional
public class BesoinService {
    private final Logger log = LoggerFactory.getLogger(BesoinService.class);

    private final BesoinRepository besoinRepository;

    private final BesoinMapper besoinMapper;

    public BesoinService(BesoinRepository besoinRepository, BesoinMapper besoinMapper) {
        this.besoinRepository = besoinRepository;
        this.besoinMapper = besoinMapper;
    }

    /**
     * Save a besoin.
     *
     * @param besoinDTO the entity to save.
     * @return the persisted entity.
     */
    public BesoinDTO save(BesoinDTO besoinDTO) {
        log.debug("Request to save Besoin : {}", besoinDTO);
        Besoin besoin = besoinMapper.toEntity(besoinDTO);
        besoin = besoinRepository.save(besoin);
        return besoinMapper.toDto(besoin);
    }

    /**
     * Get all the besoins.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BesoinDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Besoins");
        return besoinRepository.findAll(pageable).map(besoinMapper::toDto);
    }

    /**
     * Get one besoin by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BesoinDTO> findOne(Long id) {
        log.debug("Request to get Besoin : {}", id);
        return besoinRepository.findById(id).map(besoinMapper::toDto);
    }

    /**
     * Delete the besoin by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Besoin : {}", id);
        besoinRepository.deleteById(id);
    }
}
