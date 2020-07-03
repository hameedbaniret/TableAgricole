package ca.angios.tableagricole.service.mapper;

import ca.angios.tableagricole.domain.*;
import ca.angios.tableagricole.service.dto.DistanceVehDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DistanceVeh} and its DTO {@link DistanceVehDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DistanceVehMapper extends EntityMapper<DistanceVehDTO, DistanceVeh> {
    @Mapping(target = "demandes", ignore = true)
    @Mapping(target = "removeDemande", ignore = true)
    DistanceVeh toEntity(DistanceVehDTO distanceVehDTO);

    default DistanceVeh fromId(Long id) {
        if (id == null) {
            return null;
        }
        DistanceVeh distanceVeh = new DistanceVeh();
        distanceVeh.setId(id);
        return distanceVeh;
    }
}
