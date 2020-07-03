package ca.angios.tableagricole.service;

import ca.angios.tableagricole.domain.Demande;
import ca.angios.tableagricole.repository.DemandeRepository;
import ca.angios.tableagricole.service.dto.DemandeDTO;
import ca.angios.tableagricole.service.mapper.DemandeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Demande}.
 */
@Service
@Transactional
public class DemandeService {
    private final Logger log = LoggerFactory.getLogger(DemandeService.class);

    private final DemandeRepository demandeRepository;

    private final DemandeMapper demandeMapper;

    public DemandeService(DemandeRepository demandeRepository, DemandeMapper demandeMapper) {
        this.demandeRepository = demandeRepository;
        this.demandeMapper = demandeMapper;
    }

    /**
     * Save a demande.
     *
     * @param demandeDTO the entity to save.
     * @return the persisted entity.
     */
    public DemandeDTO save(DemandeDTO demandeDTO) {
        log.debug("Request to save Demande : {}", demandeDTO);
        Demande demande = demandeMapper.toEntity(demandeDTO);
        demande = demandeRepository.save(demande);
        return demandeMapper.toDto(demande);
    }

    /**
     * Get all the demandes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DemandeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Demandes");
        return demandeRepository.findAll(pageable).map(demandeMapper::toDto);
    }

    /**
     * Get one demande by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DemandeDTO> findOne(Long id) {
        log.debug("Request to get Demande : {}", id);
        return demandeRepository.findById(id).map(demandeMapper::toDto);
    }

    /**
     * Delete the demande by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Demande : {}", id);
        demandeRepository.deleteById(id);
    }
}
