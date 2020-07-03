package ca.angios.tableagricole.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EtatFrigo.
 */
@Entity
@Table(name = "etat_frigo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EtatFrigo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_etat_frigo")
    private Long idEtatFrigo;

    @NotNull
    @Column(name = "dsc_etat_frigo", nullable = false)
    private String dscEtatFrigo;

    @OneToMany(mappedBy = "etatFrigo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Demande> demandes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEtatFrigo() {
        return idEtatFrigo;
    }

    public EtatFrigo idEtatFrigo(Long idEtatFrigo) {
        this.idEtatFrigo = idEtatFrigo;
        return this;
    }

    public void setIdEtatFrigo(Long idEtatFrigo) {
        this.idEtatFrigo = idEtatFrigo;
    }

    public String getDscEtatFrigo() {
        return dscEtatFrigo;
    }

    public EtatFrigo dscEtatFrigo(String dscEtatFrigo) {
        this.dscEtatFrigo = dscEtatFrigo;
        return this;
    }

    public void setDscEtatFrigo(String dscEtatFrigo) {
        this.dscEtatFrigo = dscEtatFrigo;
    }

    public Set<Demande> getDemandes() {
        return demandes;
    }

    public EtatFrigo demandes(Set<Demande> demandes) {
        this.demandes = demandes;
        return this;
    }

    public EtatFrigo addDemande(Demande demande) {
        this.demandes.add(demande);
        demande.setEtatFrigo(this);
        return this;
    }

    public EtatFrigo removeDemande(Demande demande) {
        this.demandes.remove(demande);
        demande.setEtatFrigo(null);
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
        if (!(o instanceof EtatFrigo)) {
            return false;
        }
        return id != null && id.equals(((EtatFrigo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EtatFrigo{" +
            "id=" + getId() +
            ", idEtatFrigo=" + getIdEtatFrigo() +
            ", dscEtatFrigo='" + getDscEtatFrigo() + "'" +
            "}";
    }
}
