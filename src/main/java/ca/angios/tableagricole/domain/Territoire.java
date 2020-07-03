package ca.angios.tableagricole.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Territoire.
 */
@Entity
@Table(name = "territoire")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Territoire implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_territoire")
    private Long idTerritoire;

    @NotNull
    @Column(name = "dsc_territoire", nullable = false)
    private String dscTerritoire;

    @OneToMany(mappedBy = "territoire")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Organisme> organismes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTerritoire() {
        return idTerritoire;
    }

    public Territoire idTerritoire(Long idTerritoire) {
        this.idTerritoire = idTerritoire;
        return this;
    }

    public void setIdTerritoire(Long idTerritoire) {
        this.idTerritoire = idTerritoire;
    }

    public String getDscTerritoire() {
        return dscTerritoire;
    }

    public Territoire dscTerritoire(String dscTerritoire) {
        this.dscTerritoire = dscTerritoire;
        return this;
    }

    public void setDscTerritoire(String dscTerritoire) {
        this.dscTerritoire = dscTerritoire;
    }

    public Set<Organisme> getOrganismes() {
        return organismes;
    }

    public Territoire organismes(Set<Organisme> organismes) {
        this.organismes = organismes;
        return this;
    }

    public Territoire addOrganisme(Organisme organisme) {
        this.organismes.add(organisme);
        organisme.setTerritoire(this);
        return this;
    }

    public Territoire removeOrganisme(Organisme organisme) {
        this.organismes.remove(organisme);
        organisme.setTerritoire(null);
        return this;
    }

    public void setOrganismes(Set<Organisme> organismes) {
        this.organismes = organismes;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Territoire)) {
            return false;
        }
        return id != null && id.equals(((Territoire) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Territoire{" +
            "id=" + getId() +
            ", idTerritoire=" + getIdTerritoire() +
            ", dscTerritoire='" + getDscTerritoire() + "'" +
            "}";
    }
}
