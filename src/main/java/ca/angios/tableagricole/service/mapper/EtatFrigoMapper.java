package ca.angios.tableagricole.service.mapper;

import ca.angios.tableagricole.domain.*;
import ca.angios.tableagricole.service.dto.EtatFrigoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtatFrigo} and its DTO {@link EtatFrigoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtatFrigoMapper extends EntityMapper<EtatFrigoDTO, EtatFrigo> {
    @Mapping(target = "demandes", ignore = true)
    @Mapping(target = "removeDemande", ignore = true)
    EtatFrigo toEntity(EtatFrigoDTO etatFrigoDTO);

    default EtatFrigo fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtatFrigo etatFrigo = new EtatFrigo();
        etatFrigo.setId(id);
        return etatFrigo;
    }
}
