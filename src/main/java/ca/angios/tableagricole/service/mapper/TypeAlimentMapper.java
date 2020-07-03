package ca.angios.tableagricole.service.mapper;

import ca.angios.tableagricole.domain.*;
import ca.angios.tableagricole.service.dto.TypeAlimentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeAliment} and its DTO {@link TypeAlimentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeAlimentMapper extends EntityMapper<TypeAlimentDTO, TypeAliment> {
    @Mapping(target = "aliments", ignore = true)
    @Mapping(target = "removeAliment", ignore = true)
    @Mapping(target = "besoins", ignore = true)
    @Mapping(target = "removeBesoin", ignore = true)
    TypeAliment toEntity(TypeAlimentDTO typeAlimentDTO);

    default TypeAliment fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeAliment typeAliment = new TypeAliment();
        typeAliment.setId(id);
        return typeAliment;
    }
}
