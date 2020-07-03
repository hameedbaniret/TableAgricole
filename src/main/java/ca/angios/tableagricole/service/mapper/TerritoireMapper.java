package ca.angios.tableagricole.service.mapper;

import ca.angios.tableagricole.domain.*;
import ca.angios.tableagricole.service.dto.TerritoireDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Territoire} and its DTO {@link TerritoireDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TerritoireMapper extends EntityMapper<TerritoireDTO, Territoire> {
    @Mapping(target = "organismes", ignore = true)
    @Mapping(target = "removeOrganisme", ignore = true)
    Territoire toEntity(TerritoireDTO territoireDTO);

    default Territoire fromId(Long id) {
        if (id == null) {
            return null;
        }
        Territoire territoire = new Territoire();
        territoire.setId(id);
        return territoire;
    }
}
