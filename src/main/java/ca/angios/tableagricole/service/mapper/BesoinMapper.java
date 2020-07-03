package ca.angios.tableagricole.service.mapper;

import ca.angios.tableagricole.domain.*;
import ca.angios.tableagricole.service.dto.BesoinDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Besoin} and its DTO {@link BesoinDTO}.
 */
@Mapper(componentModel = "spring", uses = { TypeAlimentMapper.class, AlimentMapper.class, DemandeMapper.class })
public interface BesoinMapper extends EntityMapper<BesoinDTO, Besoin> {
    @Mapping(source = "typeAliment.id", target = "typeAlimentId")
    @Mapping(source = "aliment.id", target = "alimentId")
    @Mapping(source = "demande.id", target = "demandeId")
    BesoinDTO toDto(Besoin besoin);

    @Mapping(source = "typeAlimentId", target = "typeAliment")
    @Mapping(source = "alimentId", target = "aliment")
    @Mapping(source = "demandeId", target = "demande")
    Besoin toEntity(BesoinDTO besoinDTO);

    default Besoin fromId(Long id) {
        if (id == null) {
            return null;
        }
        Besoin besoin = new Besoin();
        besoin.setId(id);
        return besoin;
    }
}
