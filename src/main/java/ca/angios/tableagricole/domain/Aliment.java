package ca.angios.tableagricole.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Aliment.
 */
@Entity
@Table(name = "aliment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Aliment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_aliment")
    private Long idAliment;

    @NotNull
    @Column(name = "dsc_aliment", nullable = false)
    private String dscAliment;

    @OneToMany(mappedBy = "aliment")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Besoin> besoins = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "aliments", allowSetters = true)
    private TypeAliment typeAliment;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAliment() {
        return idAliment;
    }

    public Aliment idAliment(Long idAliment) {
        this.idAliment = idAliment;
        return this;
    }

    public void setIdAliment(Long idAliment) {
        this.idAliment = idAliment;
    }

    public String getDscAliment() {
        return dscAliment;
    }

    public Aliment dscAliment(String dscAliment) {
        this.dscAliment = dscAliment;
        return this;
    }

    public void setDscAliment(String dscAliment) {
        this.dscAliment = dscAliment;
    }

    public Set<Besoin> getBesoins() {
        return besoins;
    }

    public Aliment besoins(Set<Besoin> besoins) {
        this.besoins = besoins;
        return this;
    }

    public Aliment addBesoin(Besoin besoin) {
        this.besoins.add(besoin);
        besoin.setAliment(this);
        return this;
    }

    public Aliment removeBesoin(Besoin besoin) {
        this.besoins.remove(besoin);
        besoin.setAliment(null);
        return this;
    }

    public void setBesoins(Set<Besoin> besoins) {
        this.besoins = besoins;
    }

    public TypeAliment getTypeAliment() {
        return typeAliment;
    }

    public Aliment typeAliment(TypeAliment typeAliment) {
        this.typeAliment = typeAliment;
        return this;
    }

    public void setTypeAliment(TypeAliment typeAliment) {
        this.typeAliment = typeAliment;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Aliment)) {
            return false;
        }
        return id != null && id.equals(((Aliment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Aliment{" +
            "id=" + getId() +
            ", idAliment=" + getIdAliment() +
            ", dscAliment='" + getDscAliment() + "'" +
            "}";
    }
}
