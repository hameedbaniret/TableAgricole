package ca.angios.tableagricole.service;

import ca.angios.tableagricole.domain.TypeAliment;
import ca.angios.tableagricole.repository.TypeAlimentRepository;
import ca.angios.tableagricole.service.dto.TypeAlimentDTO;
import ca.angios.tableagricole.service.mapper.TypeAlimentMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TypeAliment}.
 */
@Service
@Transactional
public class TypeAlimentService {
    private final Logger log = LoggerFactory.getLogger(TypeAlimentService.class);

    private final TypeAlimentRepository typeAlimentRepository;

    private final TypeAlimentMapper typeAlimentMapper;

    public TypeAlimentService(TypeAlimentRepository typeAlimentRepository, TypeAlimentMapper typeAlimentMapper) {
        this.typeAlimentRepository = typeAlimentRepository;
        this.typeAlimentMapper = typeAlimentMapper;
    }

    /**
     * Save a typeAliment.
     *
     * @param typeAlimentDTO the entity to save.
     * @return the persisted entity.
     */
    public TypeAlimentDTO save(TypeAlimentDTO typeAlimentDTO) {
        log.debug("Request to save TypeAliment : {}", typeAlimentDTO);
        TypeAliment typeAliment = typeAlimentMapper.toEntity(typeAlimentDTO);
        typeAliment = typeAlimentRepository.save(typeAliment);
        return typeAlimentMapper.toDto(typeAliment);
    }

    /**
     * Get all the typeAliments.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TypeAlimentDTO> findAll() {
        log.debug("Request to get all TypeAliments");
        return typeAlimentRepository.findAll().stream().map(typeAlimentMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one typeAliment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TypeAlimentDTO> findOne(Long id) {
        log.debug("Request to get TypeAliment : {}", id);
        return typeAlimentRepository.findById(id).map(typeAlimentMapper::toDto);
    }

    /**
     * Delete the typeAliment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TypeAliment : {}", id);
        typeAlimentRepository.deleteById(id);
    }
}
