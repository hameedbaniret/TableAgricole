package ca.angios.tableagricole.domain;

import ca.angios.tableagricole.domain.enumeration.BesoinFreq;
import ca.angios.tableagricole.domain.enumeration.TypeBesoin;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Besoin.
 */
@Entity
@Table(name = "besoin")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Besoin implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_besoin")
    private Long idBesoin;

    @Enumerated(EnumType.STRING)
    @Column(name = "besoin_frequence")
    private BesoinFreq besoinFrequence;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_besoin")
    private TypeBesoin typeBesoin;

    @Column(name = "quantite")
    private Long quantite;

    @Column(name = "date_peremption")
    private Instant datePeremption;

    @Column(name = "date_besoin")
    private Instant dateBesoin;

    @Column(name = "titre_emploi")
    private String titreEmploi;

    @Column(name = "tache_principale")
    private String tachePrincipale;

    @Column(name = "nb_heure")
    private Long nbHeure;

    @Column(name = "duree_contrat")
    private Long dureeContrat;

    @Column(name = "date_entree")
    private Instant dateEntree;

    @Column(name = "nb_beneficiaire")
    private Long nbBeneficiaire;

    @Column(name = "service_souhaite")
    private String serviceSouhaite;

    @Column(name = "date_recuperation")
    private Instant dateRecuperation;

    @ManyToOne
    @JsonIgnoreProperties(value = "besoins", allowSetters = true)
    private TypeAliment typeAliment;

    @ManyToOne
    @JsonIgnoreProperties(value = "besoins", allowSetters = true)
    private Aliment aliment;

    @ManyToOne
    @JsonIgnoreProperties(value = "besoins", allowSetters = true)
    private Demande demande;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdBesoin() {
        return idBesoin;
    }

    public Besoin idBesoin(Long idBesoin) {
        this.idBesoin = idBesoin;
        return this;
    }

    public void setIdBesoin(Long idBesoin) {
        this.idBesoin = idBesoin;
    }

    public BesoinFreq getBesoinFrequence() {
        return besoinFrequence;
    }

    public Besoin besoinFrequence(BesoinFreq besoinFrequence) {
        this.besoinFrequence = besoinFrequence;
        return this;
    }

    public void setBesoinFrequence(BesoinFreq besoinFrequence) {
        this.besoinFrequence = besoinFrequence;
    }

    public TypeBesoin getTypeBesoin() {
        return typeBesoin;
    }

    public Besoin typeBesoin(TypeBesoin typeBesoin) {
        this.typeBesoin = typeBesoin;
        return this;
    }

    public void setTypeBesoin(TypeBesoin typeBesoin) {
        this.typeBesoin = typeBesoin;
    }

    public Long getQuantite() {
        return quantite;
    }

    public Besoin quantite(Long quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }

    public Instant getDatePeremption() {
        return datePeremption;
    }

    public Besoin datePeremption(Instant datePeremption) {
        this.datePeremption = datePeremption;
        return this;
    }

    public void setDatePeremption(Instant datePeremption) {
        this.datePeremption = datePeremption;
    }

    public Instant getDateBesoin() {
        return dateBesoin;
    }

    public Besoin dateBesoin(Instant dateBesoin) {
        this.dateBesoin = dateBesoin;
        return this;
    }

    public void setDateBesoin(Instant dateBesoin) {
        this.dateBesoin = dateBesoin;
    }

    public String getTitreEmploi() {
        return titreEmploi;
    }

    public Besoin titreEmploi(String titreEmploi) {
        this.titreEmploi = titreEmploi;
        return this;
    }

    public void setTitreEmploi(String titreEmploi) {
        this.titreEmploi = titreEmploi;
    }

    public String getTachePrincipale() {
        return tachePrincipale;
    }

    public Besoin tachePrincipale(String tachePrincipale) {
        this.tachePrincipale = tachePrincipale;
        return this;
    }

    public void setTachePrincipale(String tachePrincipale) {
        this.tachePrincipale = tachePrincipale;
    }

    public Long getNbHeure() {
        return nbHeure;
    }

    public Besoin nbHeure(Long nbHeure) {
        this.nbHeure = nbHeure;
        return this;
    }

    public void setNbHeure(Long nbHeure) {
        this.nbHeure = nbHeure;
    }

    public Long getDureeContrat() {
        return dureeContrat;
    }

    public Besoin dureeContrat(Long dureeContrat) {
        this.dureeContrat = dureeContrat;
        return this;
    }

    public void setDureeContrat(Long dureeContrat) {
        this.dureeContrat = dureeContrat;
    }

    public Instant getDateEntree() {
        return dateEntree;
    }

    public Besoin dateEntree(Instant dateEntree) {
        this.dateEntree = dateEntree;
        return this;
    }

    public void setDateEntree(Instant dateEntree) {
        this.dateEntree = dateEntree;
    }

    public Long getNbBeneficiaire() {
        return nbBeneficiaire;
    }

    public Besoin nbBeneficiaire(Long nbBeneficiaire) {
        this.nbBeneficiaire = nbBeneficiaire;
        return this;
    }

    public void setNbBeneficiaire(Long nbBeneficiaire) {
        this.nbBeneficiaire = nbBeneficiaire;
    }

    public String getServiceSouhaite() {
        return serviceSouhaite;
    }

    public Besoin serviceSouhaite(String serviceSouhaite) {
        this.serviceSouhaite = serviceSouhaite;
        return this;
    }

    public void setServiceSouhaite(String serviceSouhaite) {
        this.serviceSouhaite = serviceSouhaite;
    }

    public Instant getDateRecuperation() {
        return dateRecuperation;
    }

    public Besoin dateRecuperation(Instant dateRecuperation) {
        this.dateRecuperation = dateRecuperation;
        return this;
    }

    public void setDateRecuperation(Instant dateRecuperation) {
        this.dateRecuperation = dateRecuperation;
    }

    public TypeAliment getTypeAliment() {
        return typeAliment;
    }

    public Besoin typeAliment(TypeAliment typeAliment) {
        this.typeAliment = typeAliment;
        return this;
    }

    public void setTypeAliment(TypeAliment typeAliment) {
        this.typeAliment = typeAliment;
    }

    public Aliment getAliment() {
        return aliment;
    }

    public Besoin aliment(Aliment aliment) {
        this.aliment = aliment;
        return this;
    }

    public void setAliment(Aliment aliment) {
        this.aliment = aliment;
    }

    public Demande getDemande() {
        return demande;
    }

    public Besoin demande(Demande demande) {
        this.demande = demande;
        return this;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Besoin)) {
            return false;
        }
        return id != null && id.equals(((Besoin) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Besoin{" +
            "id=" + getId() +
            ", idBesoin=" + getIdBesoin() +
            ", besoinFrequence='" + getBesoinFrequence() + "'" +
            ", typeBesoin='" + getTypeBesoin() + "'" +
            ", quantite=" + getQuantite() +
            ", datePeremption='" + getDatePeremption() + "'" +
            ", dateBesoin='" + getDateBesoin() + "'" +
            ", titreEmploi='" + getTitreEmploi() + "'" +
            ", tachePrincipale='" + getTachePrincipale() + "'" +
            ", nbHeure=" + getNbHeure() +
            ", dureeContrat=" + getDureeContrat() +
            ", dateEntree='" + getDateEntree() + "'" +
            ", nbBeneficiaire=" + getNbBeneficiaire() +
            ", serviceSouhaite='" + getServiceSouhaite() + "'" +
            ", dateRecuperation='" + getDateRecuperation() + "'" +
            "}";
    }
}
