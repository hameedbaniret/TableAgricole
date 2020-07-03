package ca.angios.tableagricole.service.mapper;

import ca.angios.tableagricole.domain.*;
import ca.angios.tableagricole.service.dto.DistanceVeloDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DistanceVelo} and its DTO {@link DistanceVeloDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DistanceVeloMapper extends EntityMapper<DistanceVeloDTO, DistanceVelo> {
    @Mapping(target = "demandes", ignore = true)
    @Mapping(target = "removeDemande", ignore = true)
    DistanceVelo toEntity(DistanceVeloDTO distanceVeloDTO);

    default DistanceVelo fromId(Long id) {
        if (id == null) {
            return null;
        }
        DistanceVelo distanceVelo = new DistanceVelo();
        distanceVelo.setId(id);
        return distanceVelo;
    }
}
