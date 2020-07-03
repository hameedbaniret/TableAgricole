package ca.angios.tableagricole.service.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link ca.angios.tableagricole.domain.DistanceVelo} entity.
 */
public class DistanceVeloDTO implements Serializable {
    private Long id;

    private Long idDistanceVelo;

    @NotNull
    private String dscDistanceVelo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDistanceVelo() {
        return idDistanceVelo;
    }

    public void setIdDistanceVelo(Long idDistanceVelo) {
        this.idDistanceVelo = idDistanceVelo;
    }

    public String getDscDistanceVelo() {
        return dscDistanceVelo;
    }

    public void setDscDistanceVelo(String dscDistanceVelo) {
        this.dscDistanceVelo = dscDistanceVelo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DistanceVeloDTO)) {
            return false;
        }

        return id != null && id.equals(((DistanceVeloDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DistanceVeloDTO{" +
            "id=" + getId() +
            ", idDistanceVelo=" + getIdDistanceVelo() +
            ", dscDistanceVelo='" + getDscDistanceVelo() + "'" +
            "}";
    }
}
