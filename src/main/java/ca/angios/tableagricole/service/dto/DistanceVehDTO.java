package ca.angios.tableagricole.service.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link ca.angios.tableagricole.domain.DistanceVeh} entity.
 */
public class DistanceVehDTO implements Serializable {
    private Long id;

    private Long idDistanceVeh;

    @NotNull
    private String dscDistanceVeh;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDistanceVeh() {
        return idDistanceVeh;
    }

    public void setIdDistanceVeh(Long idDistanceVeh) {
        this.idDistanceVeh = idDistanceVeh;
    }

    public String getDscDistanceVeh() {
        return dscDistanceVeh;
    }

    public void setDscDistanceVeh(String dscDistanceVeh) {
        this.dscDistanceVeh = dscDistanceVeh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DistanceVehDTO)) {
            return false;
        }

        return id != null && id.equals(((DistanceVehDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DistanceVehDTO{" +
            "id=" + getId() +
            ", idDistanceVeh=" + getIdDistanceVeh() +
            ", dscDistanceVeh='" + getDscDistanceVeh() + "'" +
            "}";
    }
}
