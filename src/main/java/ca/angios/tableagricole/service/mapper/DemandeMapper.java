package ca.angios.tableagricole.service.mapper;

import ca.angios.tableagricole.domain.*;
import ca.angios.tableagricole.service.dto.DemandeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Demande} and its DTO {@link DemandeDTO}.
 */
@Mapper(
    componentModel = "spring",
    uses = { DistanceVehMapper.class, OrganismeMapper.class, DistanceVeloMapper.class, EtatFrigoMapper.class }
)
public interface DemandeMapper extends EntityMapper<DemandeDTO, Demande> {
    @Mapping(source = "distanceVeh.id", target = "distanceVehId")
    @Mapping(source = "organisme.id", target = "organismeId")
    @Mapping(source = "distanceVelo.id", target = "distanceVeloId")
    @Mapping(source = "etatFrigo.id", target = "etatFrigoId")
    DemandeDTO toDto(Demande demande);

    @Mapping(target = "besoins", ignore = true)
    @Mapping(target = "removeBesoin", ignore = true)
    @Mapping(source = "distanceVehId", target = "distanceVeh")
    @Mapping(source = "organismeId", target = "organisme")
    @Mapping(source = "distanceVeloId", target = "distanceVelo")
    @Mapping(source = "etatFrigoId", target = "etatFrigo")
    Demande toEntity(DemandeDTO demandeDTO);

    default Demande fromId(Long id) {
        if (id == null) {
            return null;
        }
        Demande demande = new Demande();
        demande.setId(id);
        return demande;
    }
}
