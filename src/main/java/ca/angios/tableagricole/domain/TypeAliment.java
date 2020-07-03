package ca.angios.tableagricole.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TypeAliment.
 */
@Entity
@Table(name = "type_aliment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TypeAliment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_type_aliment")
    private Long idTypeAliment;

    @NotNull
    @Column(name = "dsc_type_aliment", nullable = false)
    private String dscTypeAliment;

    @OneToMany(mappedBy = "typeAliment")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Aliment> aliments = new HashSet<>();

    @OneToMany(mappedBy = "typeAliment")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Besoin> besoins = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTypeAliment() {
        return idTypeAliment;
    }

    public TypeAliment idTypeAliment(Long idTypeAliment) {
        this.idTypeAliment = idTypeAliment;
        return this;
    }

    public void setIdTypeAliment(Long idTypeAliment) {
        this.idTypeAliment = idTypeAliment;
    }

    public String getDscTypeAliment() {
        return dscTypeAliment;
    }

    public TypeAliment dscTypeAliment(String dscTypeAliment) {
        this.dscTypeAliment = dscTypeAliment;
        return this;
    }

    public void setDscTypeAliment(String dscTypeAliment) {
        this.dscTypeAliment = dscTypeAliment;
    }

    public Set<Aliment> getAliments() {
        return aliments;
    }

    public TypeAliment aliments(Set<Aliment> aliments) {
        this.aliments = aliments;
        return this;
    }

    public TypeAliment addAliment(Aliment aliment) {
        this.aliments.add(aliment);
        aliment.setTypeAliment(this);
        return this;
    }

    public TypeAliment removeAliment(Aliment aliment) {
        this.aliments.remove(aliment);
        aliment.setTypeAliment(null);
        return this;
    }

    public void setAliments(Set<Aliment> aliments) {
        this.aliments = aliments;
    }

    public Set<Besoin> getBesoins() {
        return besoins;
    }

    public TypeAliment besoins(Set<Besoin> besoins) {
        this.besoins = besoins;
        return this;
    }

    public TypeAliment addBesoin(Besoin besoin) {
        this.besoins.add(besoin);
        besoin.setTypeAliment(this);
        return this;
    }

    public TypeAliment removeBesoin(Besoin besoin) {
        this.besoins.remove(besoin);
        besoin.setTypeAliment(null);
        return this;
    }

    public void setBesoins(Set<Besoin> besoins) {
        this.besoins = besoins;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeAliment)) {
            return false;
        }
        return id != null && id.equals(((TypeAliment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeAliment{" +
            "id=" + getId() +
            ", idTypeAliment=" + getIdTypeAliment() +
            ", dscTypeAliment='" + getDscTypeAliment() + "'" +
            "}";
    }
}
