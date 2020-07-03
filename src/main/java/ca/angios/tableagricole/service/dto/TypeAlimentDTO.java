package ca.angios.tableagricole.service.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link ca.angios.tableagricole.domain.TypeAliment} entity.
 */
public class TypeAlimentDTO implements Serializable {
    private Long id;

    private Long idTypeAliment;

    @NotNull
    private String dscTypeAliment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTypeAliment() {
        return idTypeAliment;
    }

    public void setIdTypeAliment(Long idTypeAliment) {
        this.idTypeAliment = idTypeAliment;
    }

    public String getDscTypeAliment() {
        return dscTypeAliment;
    }

    public void setDscTypeAliment(String dscTypeAliment) {
        this.dscTypeAliment = dscTypeAliment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeAlimentDTO)) {
            return false;
        }

        return id != null && id.equals(((TypeAlimentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeAlimentDTO{" +
            "id=" + getId() +
            ", idTypeAliment=" + getIdTypeAliment() +
            ", dscTypeAliment='" + getDscTypeAliment() + "'" +
            "}";
    }
}
