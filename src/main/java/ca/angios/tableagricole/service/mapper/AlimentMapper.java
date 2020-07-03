package ca.angios.tableagricole.service.mapper;

import ca.angios.tableagricole.domain.*;
import ca.angios.tableagricole.service.dto.AlimentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Aliment} and its DTO {@link AlimentDTO}.
 */
@Mapper(componentModel = "spring", uses = { TypeAlimentMapper.class })
public interface AlimentMapper extends EntityMapper<AlimentDTO, Aliment> {
    @Mapping(source = "typeAliment.id", target = "typeAlimentId")
    AlimentDTO toDto(Aliment aliment);

    @Mapping(target = "besoins", ignore = true)
    @Mapping(target = "removeBesoin", ignore = true)
    @Mapping(source = "typeAlimentId", target = "typeAliment")
    Aliment toEntity(AlimentDTO alimentDTO);

    default Aliment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Aliment aliment = new Aliment();
        aliment.setId(id);
        return aliment;
    }
}
