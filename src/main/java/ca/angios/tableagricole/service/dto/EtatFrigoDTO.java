package ca.angios.tableagricole.service.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link ca.angios.tableagricole.domain.EtatFrigo} entity.
 */
public class EtatFrigoDTO implements Serializable {
    private Long id;

    private Long idEtatFrigo;

    @NotNull
    private String dscEtatFrigo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEtatFrigo() {
        return idEtatFrigo;
    }

    public void setIdEtatFrigo(Long idEtatFrigo) {
        this.idEtatFrigo = idEtatFrigo;
    }

    public String getDscEtatFrigo() {
        return dscEtatFrigo;
    }

    public void setDscEtatFrigo(String dscEtatFrigo) {
        this.dscEtatFrigo = dscEtatFrigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EtatFrigoDTO)) {
            return false;
        }

        return id != null && id.equals(((EtatFrigoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EtatFrigoDTO{" +
            "id=" + getId() +
            ", idEtatFrigo=" + getIdEtatFrigo() +
            ", dscEtatFrigo='" + getDscEtatFrigo() + "'" +
            "}";
    }
}
