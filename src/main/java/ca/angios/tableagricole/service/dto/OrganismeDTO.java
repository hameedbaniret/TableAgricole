package ca.angios.tableagricole.service.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link ca.angios.tableagricole.domain.Organisme} entity.
 */
public class OrganismeDTO implements Serializable {
    private Long id;

    private Long idOrganisme;

    @NotNull
    private String nomOrganisme;

    @NotNull
    private String adresseOrganisme;

    private String phoneOrganisme;

    private String courrielOrganisme;

    private Long territoireId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOrganisme() {
        return idOrganisme;
    }

    public void setIdOrganisme(Long idOrganisme) {
        this.idOrganisme = idOrganisme;
    }

    public String getNomOrganisme() {
        return nomOrganisme;
    }

    public void setNomOrganisme(String nomOrganisme) {
        this.nomOrganisme = nomOrganisme;
    }

    public String getAdresseOrganisme() {
        return adresseOrganisme;
    }

    public void setAdresseOrganisme(String adresseOrganisme) {
        this.adresseOrganisme = adresseOrganisme;
    }

    public String getPhoneOrganisme() {
        return phoneOrganisme;
    }

    public void setPhoneOrganisme(String phoneOrganisme) {
        this.phoneOrganisme = phoneOrganisme;
    }

    public String getCourrielOrganisme() {
        return courrielOrganisme;
    }

    public void setCourrielOrganisme(String courrielOrganisme) {
        this.courrielOrganisme = courrielOrganisme;
    }

    public Long getTerritoireId() {
        return territoireId;
    }

    public void setTerritoireId(Long territoireId) {
        this.territoireId = territoireId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrganismeDTO)) {
            return false;
        }

        return id != null && id.equals(((OrganismeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrganismeDTO{" +
            "id=" + getId() +
            ", idOrganisme=" + getIdOrganisme() +
            ", nomOrganisme='" + getNomOrganisme() + "'" +
            ", adresseOrganisme='" + getAdresseOrganisme() + "'" +
            ", phoneOrganisme='" + getPhoneOrganisme() + "'" +
            ", courrielOrganisme='" + getCourrielOrganisme() + "'" +
            ", territoireId=" + getTerritoireId() +
            "}";
    }
}
