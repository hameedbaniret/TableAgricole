package ca.angios.tableagricole.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DistanceVeh.
 */
@Entity
@Table(name = "distance_veh")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DistanceVeh implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_distance_veh")
    private Long idDistanceVeh;

    @NotNull
    @Column(name = "dsc_distance_veh", nullable = false)
    private String dscDistanceVeh;

    @OneToMany(mappedBy = "distanceVeh")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Demande> demandes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDistanceVeh() {
        return idDistanceVeh;
    }

    public DistanceVeh idDistanceVeh(Long idDistanceVeh) {
        this.idDistanceVeh = idDistanceVeh;
        return this;
    }

    public void setIdDistanceVeh(Long idDistanceVeh) {
        this.idDistanceVeh = idDistanceVeh;
    }

    public String getDscDistanceVeh() {
        return dscDistanceVeh;
    }

    public DistanceVeh dscDistanceVeh(String dscDistanceVeh) {
        this.dscDistanceVeh = dscDistanceVeh;
        return this;
    }

    public void setDscDistanceVeh(String dscDistanceVeh) {
        this.dscDistanceVeh = dscDistanceVeh;
    }

    public Set<Demande> getDemandes() {
        return demandes;
    }

    public DistanceVeh demandes(Set<Demande> demandes) {
        this.demandes = demandes;
        return this;
    }

    public DistanceVeh addDemande(Demande demande) {
        this.demandes.add(demande);
        demande.setDistanceVeh(this);
        return this;
    }

    public DistanceVeh removeDemande(Demande demande) {
        this.demandes.remove(demande);
        demande.setDistanceVeh(null);
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
        if (!(o instanceof DistanceVeh)) {
            return false;
        }
        return id != null && id.equals(((DistanceVeh) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DistanceVeh{" +
            "id=" + getId() +
            ", idDistanceVeh=" + getIdDistanceVeh() +
            ", dscDistanceVeh='" + getDscDistanceVeh() + "'" +
            "}";
    }
}
