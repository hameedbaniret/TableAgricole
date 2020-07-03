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
 * A Organisme.
 */
@Entity
@Table(name = "organisme")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Organisme implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_organisme")
    private Long idOrganisme;

    @NotNull
    @Column(name = "nom_organisme", nullable = false)
    private String nomOrganisme;

    @NotNull
    @Column(name = "adresse_organisme", nullable = false)
    private String adresseOrganisme;

    @Column(name = "phone_organisme")
    private String phoneOrganisme;

    @Column(name = "courriel_organisme")
    private String courrielOrganisme;

    @OneToMany(mappedBy = "organisme")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Demande> demandes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "organismes", allowSetters = true)
    private Territoire territoire;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOrganisme() {
        return idOrganisme;
    }

    public Organisme idOrganisme(Long idOrganisme) {
        this.idOrganisme = idOrganisme;
        return this;
    }

    public void setIdOrganisme(Long idOrganisme) {
        this.idOrganisme = idOrganisme;
    }

    public String getNomOrganisme() {
        return nomOrganisme;
    }

    public Organisme nomOrganisme(String nomOrganisme) {
        this.nomOrganisme = nomOrganisme;
        return this;
    }

    public void setNomOrganisme(String nomOrganisme) {
        this.nomOrganisme = nomOrganisme;
    }

    public String getAdresseOrganisme() {
        return adresseOrganisme;
    }

    public Organisme adresseOrganisme(String adresseOrganisme) {
        this.adresseOrganisme = adresseOrganisme;
        return this;
    }

    public void setAdresseOrganisme(String adresseOrganisme) {
        this.adresseOrganisme = adresseOrganisme;
    }

    public String getPhoneOrganisme() {
        return phoneOrganisme;
    }

    public Organisme phoneOrganisme(String phoneOrganisme) {
        this.phoneOrganisme = phoneOrganisme;
        return this;
    }

    public void setPhoneOrganisme(String phoneOrganisme) {
        this.phoneOrganisme = phoneOrganisme;
    }

    public String getCourrielOrganisme() {
        return courrielOrganisme;
    }

    public Organisme courrielOrganisme(String courrielOrganisme) {
        this.courrielOrganisme = courrielOrganisme;
        return this;
    }

    public void setCourrielOrganisme(String courrielOrganisme) {
        this.courrielOrganisme = courrielOrganisme;
    }

    public Set<Demande> getDemandes() {
        return demandes;
    }

    public Organisme demandes(Set<Demande> demandes) {
        this.demandes = demandes;
        return this;
    }

    public Organisme addDemande(Demande demande) {
        this.demandes.add(demande);
        demande.setOrganisme(this);
        return this;
    }

    public Organisme removeDemande(Demande demande) {
        this.demandes.remove(demande);
        demande.setOrganisme(null);
        return this;
    }

    public void setDemandes(Set<Demande> demandes) {
        this.demandes = demandes;
    }

    public Territoire getTerritoire() {
        return territoire;
    }

    public Organisme territoire(Territoire territoire) {
        this.territoire = territoire;
        return this;
    }

    public void setTerritoire(Territoire territoire) {
        this.territoire = territoire;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organisme)) {
            return false;
        }
        return id != null && id.equals(((Organisme) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Organisme{" +
            "id=" + getId() +
            ", idOrganisme=" + getIdOrganisme() +
            ", nomOrganisme='" + getNomOrganisme() + "'" +
            ", adresseOrganisme='" + getAdresseOrganisme() + "'" +
            ", phoneOrganisme='" + getPhoneOrganisme() + "'" +
            ", courrielOrganisme='" + getCourrielOrganisme() + "'" +
            "}";
    }
}
