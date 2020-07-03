package ca.angios.tableagricole.service.dto;

import ca.angios.tableagricole.domain.enumeration.BesoinFreq;
import ca.angios.tableagricole.domain.enumeration.TypeBesoin;
import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link ca.angios.tableagricole.domain.Besoin} entity.
 */
public class BesoinDTO implements Serializable {
    private Long id;

    private Long idBesoin;

    private BesoinFreq besoinFrequence;

    private TypeBesoin typeBesoin;

    private Long quantite;

    private Instant datePeremption;

    private Instant dateBesoin;

    private String titreEmploi;

    private String tachePrincipale;

    private Long nbHeure;

    private Long dureeContrat;

    private Instant dateEntree;

    private Long nbBeneficiaire;

    private String serviceSouhaite;

    private Instant dateRecuperation;

    private Long typeAlimentId;

    private Long alimentId;

    private Long demandeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdBesoin() {
        return idBesoin;
    }

    public void setIdBesoin(Long idBesoin) {
        this.idBesoin = idBesoin;
    }

    public BesoinFreq getBesoinFrequence() {
        return besoinFrequence;
    }

    public void setBesoinFrequence(BesoinFreq besoinFrequence) {
        this.besoinFrequence = besoinFrequence;
    }

    public TypeBesoin getTypeBesoin() {
        return typeBesoin;
    }

    public void setTypeBesoin(TypeBesoin typeBesoin) {
        this.typeBesoin = typeBesoin;
    }

    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }

    public Instant getDatePeremption() {
        return datePeremption;
    }

    public void setDatePeremption(Instant datePeremption) {
        this.datePeremption = datePeremption;
    }

    public Instant getDateBesoin() {
        return dateBesoin;
    }

    public void setDateBesoin(Instant dateBesoin) {
        this.dateBesoin = dateBesoin;
    }

    public String getTitreEmploi() {
        return titreEmploi;
    }

    public void setTitreEmploi(String titreEmploi) {
        this.titreEmploi = titreEmploi;
    }

    public String getTachePrincipale() {
        return tachePrincipale;
    }

    public void setTachePrincipale(String tachePrincipale) {
        this.tachePrincipale = tachePrincipale;
    }

    public Long getNbHeure() {
        return nbHeure;
    }

    public void setNbHeure(Long nbHeure) {
        this.nbHeure = nbHeure;
    }

    public Long getDureeContrat() {
        return dureeContrat;
    }

    public void setDureeContrat(Long dureeContrat) {
        this.dureeContrat = dureeContrat;
    }

    public Instant getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(Instant dateEntree) {
        this.dateEntree = dateEntree;
    }

    public Long getNbBeneficiaire() {
        return nbBeneficiaire;
    }

    public void setNbBeneficiaire(Long nbBeneficiaire) {
        this.nbBeneficiaire = nbBeneficiaire;
    }

    public String getServiceSouhaite() {
        return serviceSouhaite;
    }

    public void setServiceSouhaite(String serviceSouhaite) {
        this.serviceSouhaite = serviceSouhaite;
    }

    public Instant getDateRecuperation() {
        return dateRecuperation;
    }

    public void setDateRecuperation(Instant dateRecuperation) {
        this.dateRecuperation = dateRecuperation;
    }

    public Long getTypeAlimentId() {
        return typeAlimentId;
    }

    public void setTypeAlimentId(Long typeAlimentId) {
        this.typeAlimentId = typeAlimentId;
    }

    public Long getAlimentId() {
        return alimentId;
    }

    public void setAlimentId(Long alimentId) {
        this.alimentId = alimentId;
    }

    public Long getDemandeId() {
        return demandeId;
    }

    public void setDemandeId(Long demandeId) {
        this.demandeId = demandeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BesoinDTO)) {
            return false;
        }

        return id != null && id.equals(((BesoinDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BesoinDTO{" +
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
            ", typeAlimentId=" + getTypeAlimentId() +
            ", alimentId=" + getAlimentId() +
            ", demandeId=" + getDemandeId() +
            "}";
    }
}
