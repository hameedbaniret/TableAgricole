package ca.angios.tableagricole.service.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link ca.angios.tableagricole.domain.Territoire} entity.
 */
public class TerritoireDTO implements Serializable {
    private Long id;

    private Long idTerritoire;

    @NotNull
    private String dscTerritoire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTerritoire() {
        return idTerritoire;
    }

    public void setIdTerritoire(Long idTerritoire) {
        this.idTerritoire = idTerritoire;
    }

    public String getDscTerritoire() {
        return dscTerritoire;
    }

    public void setDscTerritoire(String dscTerritoire) {
        this.dscTerritoire = dscTerritoire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TerritoireDTO)) {
            return false;
        }

        return id != null && id.equals(((TerritoireDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TerritoireDTO{" +
            "id=" + getId() +
            ", idTerritoire=" + getIdTerritoire() +
            ", dscTerritoire='" + getDscTerritoire() + "'" +
            "}";
    }
}
