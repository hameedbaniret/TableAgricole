package ca.angios.tableagricole.service.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link ca.angios.tableagricole.domain.Aliment} entity.
 */
public class AlimentDTO implements Serializable {
    private Long id;

    private Long idAliment;

    @NotNull
    private String dscAliment;

    private Long typeAlimentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAliment() {
        return idAliment;
    }

    public void setIdAliment(Long idAliment) {
        this.idAliment = idAliment;
    }

    public String getDscAliment() {
        return dscAliment;
    }

    public void setDscAliment(String dscAliment) {
        this.dscAliment = dscAliment;
    }

    public Long getTypeAlimentId() {
        return typeAlimentId;
    }

    public void setTypeAlimentId(Long typeAlimentId) {
        this.typeAlimentId = typeAlimentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AlimentDTO)) {
            return false;
        }

        return id != null && id.equals(((AlimentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AlimentDTO{" +
            "id=" + getId() +
            ", idAliment=" + getIdAliment() +
            ", dscAliment='" + getDscAliment() + "'" +
            ", typeAlimentId=" + getTypeAlimentId() +
            "}";
    }
}
