package ca.angios.tableagricole.service.mapper;

import ca.angios.tableagricole.domain.*;
import ca.angios.tableagricole.service.dto.OrganismeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Organisme} and its DTO {@link OrganismeDTO}.
 */
@Mapper(componentModel = "spring", uses = { TerritoireMapper.class })
public interface OrganismeMapper extends EntityMapper<OrganismeDTO, Organisme> {
    @Mapping(source = "territoire.id", target = "territoireId")
    OrganismeDTO toDto(Organisme organisme);

    @Mapping(target = "demandes", ignore = true)
    @Mapping(target = "removeDemande", ignore = true)
    @Mapping(source = "territoireId", target = "territoire")
    Organisme toEntity(OrganismeDTO organismeDTO);

    default Organisme fromId(Long id) {
        if (id == null) {
            return null;
        }
        Organisme organisme = new Organisme();
        organisme.setId(id);
        return organisme;
    }
}
