package ca.angios.tableagricole.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DistanceVelo.
 */
@Entity
@Table(name = "distance_velo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DistanceVelo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_distance_velo")
    private Long idDistanceVelo;

    @NotNull
    @Column(name = "dsc_distance_velo", nullable = false)
    private String dscDistanceVelo;

    @OneToMany(mappedBy = "distanceVelo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Demande> demandes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDistanceVelo() {
        return idDistanceVelo;
    }

    public DistanceVelo idDistanceVelo(Long idDistanceVelo) {
        this.idDistanceVelo = idDistanceVelo;
        return this;
    }

    public void setIdDistanceVelo(Long idDistanceVelo) {
        this.idDistanceVelo = idDistanceVelo;
    }

    public String getDscDistanceVelo() {
        return dscDistanceVelo;
    }

    public DistanceVelo dscDistanceVelo(String dscDistanceVelo) {
        this.dscDistanceVelo = dscDistanceVelo;
        return this;
    }

    public void setDscDistanceVelo(String dscDistanceVelo) {
        this.dscDistanceVelo = dscDistanceVelo;
    }

    public Set<Demande> getDemandes() {
        return demandes;
    }

    public DistanceVelo demandes(Set<Demande> demandes) {
        this.demandes = demandes;
        return this;
    }

    public DistanceVelo addDemande(Demande demande) {
        this.demandes.add(demande);
        demande.setDistanceVelo(this);
        return this;
    }

    public DistanceVelo removeDemande(Demande demande) {
        this.demandes.remove(demande);
        demande.setDistanceVelo(null);
        return this;
    }

    public void setDemandes(Set<Demande> demandes) {
        this.demandes = demandes;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DistanceVelo)) {
            return false;
        }
        return id != null && id.equals(((DistanceVelo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DistanceVelo{" +
            "id=" + getId() +
            ", idDistanceVelo=" + getIdDistanceVelo() +
            ", dscDistanceVelo='" + getDscDistanceVelo() + "'" +
            "}";
    }
}
