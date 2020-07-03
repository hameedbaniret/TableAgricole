package ca.angios.tableagricole.service.dto;

import ca.angios.tableagricole.domain.enumeration.Frequence;
import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link ca.angios.tableagricole.domain.Demande} entity.
 */
public class DemandeDTO implements Serializable {
    private Long id;

    private Long idDemande;

    private Frequence frequence;

    private Boolean flagTypeDepDepannageUrgence;

    private Boolean flagTypeDepDepannageRegulier;

    private Boolean flagTypeDepPopoteRoulante;

    private Boolean flagTypeDepMarcheAbordable;

    private Boolean flagTypeDepEntraideAlimentaire;

    private Boolean flagTypeDepFrigoPartage;

    private Boolean flagTypeDepAutre;

    private String besoinTechnique;

    private String besoinUrgent;

    private Boolean flagCamionRegrigere;

    private Boolean flagPasCongelateur;

    private Boolean flagCongelateurInd;

    private Boolean flagCongelateurPlainPied;

    private Boolean flagCongelateurResidentiel;

    private Boolean flagPasRefrigerateur;

    private Boolean flagRefrigerateurInd;

    private Boolean flagRefrigerateurPlainPied;

    private Boolean flagRefrigerateurResidentiel;

    private Boolean flagAccesGlaciere;

    private Boolean flagAccesCuisine;

    private Boolean flagAccesVehicule;

    private Instant dateInspection;

    private Long nombreBeneficiaire;

    private Long prcAugmentationNbBenef;

    private Long prcRHPerdue;

    private Long prcBenevolePerdu;

    private Boolean flagDenreeSuffisante;

    private Boolean flagRHSuffisant;

    private Boolean flagVegetarien;

    private Boolean flagVegetalien;

    private Boolean flagItemSansEtiquette;

    private Boolean flagItemDate;

    private Boolean flagHalal;

    private Boolean flagKasher;

    private String boiteSuggestion;

    private String autreRessource;

    private Long distanceVehId;

    private Long organismeId;

    private Long distanceVeloId;

    private Long etatFrigoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(Long idDemande) {
        this.idDemande = idDemande;
    }

    public Frequence getFrequence() {
        return frequence;
    }

    public void setFrequence(Frequence frequence) {
        this.frequence = frequence;
    }

    public Boolean isFlagTypeDepDepannageUrgence() {
        return flagTypeDepDepannageUrgence;
    }

    public void setFlagTypeDepDepannageUrgence(Boolean flagTypeDepDepannageUrgence) {
        this.flagTypeDepDepannageUrgence = flagTypeDepDepannageUrgence;
    }

    public Boolean isFlagTypeDepDepannageRegulier() {
        return flagTypeDepDepannageRegulier;
    }

    public void setFlagTypeDepDepannageRegulier(Boolean flagTypeDepDepannageRegulier) {
        this.flagTypeDepDepannageRegulier = flagTypeDepDepannageRegulier;
    }

    public Boolean isFlagTypeDepPopoteRoulante() {
        return flagTypeDepPopoteRoulante;
    }

    public void setFlagTypeDepPopoteRoulante(Boolean flagTypeDepPopoteRoulante) {
        this.flagTypeDepPopoteRoulante = flagTypeDepPopoteRoulante;
    }

    public Boolean isFlagTypeDepMarcheAbordable() {
        return flagTypeDepMarcheAbordable;
    }

    public void setFlagTypeDepMarcheAbordable(Boolean flagTypeDepMarcheAbordable) {
        this.flagTypeDepMarcheAbordable = flagTypeDepMarcheAbordable;
    }

    public Boolean isFlagTypeDepEntraideAlimentaire() {
        return flagTypeDepEntraideAlimentaire;
    }

    public void setFlagTypeDepEntraideAlimentaire(Boolean flagTypeDepEntraideAlimentaire) {
        this.flagTypeDepEntraideAlimentaire = flagTypeDepEntraideAlimentaire;
    }

    public Boolean isFlagTypeDepFrigoPartage() {
        return flagTypeDepFrigoPartage;
    }

    public void setFlagTypeDepFrigoPartage(Boolean flagTypeDepFrigoPartage) {
        this.flagTypeDepFrigoPartage = flagTypeDepFrigoPartage;
    }

    public Boolean isFlagTypeDepAutre() {
        return flagTypeDepAutre;
    }

    public void setFlagTypeDepAutre(Boolean flagTypeDepAutre) {
        this.flagTypeDepAutre = flagTypeDepAutre;
    }

    public String getBesoinTechnique() {
        return besoinTechnique;
    }

    public void setBesoinTechnique(String besoinTechnique) {
        this.besoinTechnique = besoinTechnique;
    }

    public String getBesoinUrgent() {
        return besoinUrgent;
    }

    public void setBesoinUrgent(String besoinUrgent) {
        this.besoinUrgent = besoinUrgent;
    }

    public Boolean isFlagCamionRegrigere() {
        return flagCamionRegrigere;
    }

    public void setFlagCamionRegrigere(Boolean flagCamionRegrigere) {
        this.flagCamionRegrigere = flagCamionRegrigere;
    }

    public Boolean isFlagPasCongelateur() {
        return flagPasCongelateur;
    }

    public void setFlagPasCongelateur(Boolean flagPasCongelateur) {
        this.flagPasCongelateur = flagPasCongelateur;
    }

    public Boolean isFlagCongelateurInd() {
        return flagCongelateurInd;
    }

    public void setFlagCongelateurInd(Boolean flagCongelateurInd) {
        this.flagCongelateurInd = flagCongelateurInd;
    }

    public Boolean isFlagCongelateurPlainPied() {
        return flagCongelateurPlainPied;
    }

    public void setFlagCongelateurPlainPied(Boolean flagCongelateurPlainPied) {
        this.flagCongelateurPlainPied = flagCongelateurPlainPied;
    }

    public Boolean isFlagCongelateurResidentiel() {
        return flagCongelateurResidentiel;
    }

    public void setFlagCongelateurResidentiel(Boolean flagCongelateurResidentiel) {
        this.flagCongelateurResidentiel = flagCongelateurResidentiel;
    }

    public Boolean isFlagPasRefrigerateur() {
        return flagPasRefrigerateur;
    }

    public void setFlagPasRefrigerateur(Boolean flagPasRefrigerateur) {
        this.flagPasRefrigerateur = flagPasRefrigerateur;
    }

    public Boolean isFlagRefrigerateurInd() {
        return flagRefrigerateurInd;
    }

    public void setFlagRefrigerateurInd(Boolean flagRefrigerateurInd) {
        this.flagRefrigerateurInd = flagRefrigerateurInd;
    }

    public Boolean isFlagRefrigerateurPlainPied() {
        return flagRefrigerateurPlainPied;
    }

    public void setFlagRefrigerateurPlainPied(Boolean flagRefrigerateurPlainPied) {
        this.flagRefrigerateurPlainPied = flagRefrigerateurPlainPied;
    }

    public Boolean isFlagRefrigerateurResidentiel() {
        return flagRefrigerateurResidentiel;
    }

    public void setFlagRefrigerateurResidentiel(Boolean flagRefrigerateurResidentiel) {
        this.flagRefrigerateurResidentiel = flagRefrigerateurResidentiel;
    }

    public Boolean isFlagAccesGlaciere() {
        return flagAccesGlaciere;
    }

    public void setFlagAccesGlaciere(Boolean flagAccesGlaciere) {
        this.flagAccesGlaciere = flagAccesGlaciere;
    }

    public Boolean isFlagAccesCuisine() {
        return flagAccesCuisine;
    }

    public void setFlagAccesCuisine(Boolean flagAccesCuisine) {
        this.flagAccesCuisine = flagAccesCuisine;
    }

    public Boolean isFlagAccesVehicule() {
        return flagAccesVehicule;
    }

    public void setFlagAccesVehicule(Boolean flagAccesVehicule) {
        this.flagAccesVehicule = flagAccesVehicule;
    }

    public Instant getDateInspection() {
        return dateInspection;
    }

    public void setDateInspection(Instant dateInspection) {
        this.dateInspection = dateInspection;
    }

    public Long getNombreBeneficiaire() {
        return nombreBeneficiaire;
    }

    public void setNombreBeneficiaire(Long nombreBeneficiaire) {
        this.nombreBeneficiaire = nombreBeneficiaire;
    }

    public Long getPrcAugmentationNbBenef() {
        return prcAugmentationNbBenef;
    }

    public void setPrcAugmentationNbBenef(Long prcAugmentationNbBenef) {
        this.prcAugmentationNbBenef = prcAugmentationNbBenef;
    }

    public Long getPrcRHPerdue() {
        return prcRHPerdue;
    }

    public void setPrcRHPerdue(Long prcRHPerdue) {
        this.prcRHPerdue = prcRHPerdue;
    }

    public Long getPrcBenevolePerdu() {
        return prcBenevolePerdu;
    }

    public void setPrcBenevolePerdu(Long prcBenevolePerdu) {
        this.prcBenevolePerdu = prcBenevolePerdu;
    }

    public Boolean isFlagDenreeSuffisante() {
        return flagDenreeSuffisante;
    }

    public void setFlagDenreeSuffisante(Boolean flagDenreeSuffisante) {
        this.flagDenreeSuffisante = flagDenreeSuffisante;
    }

    public Boolean isFlagRHSuffisant() {
        return flagRHSuffisant;
    }

    public void setFlagRHSuffisant(Boolean flagRHSuffisant) {
        this.flagRHSuffisant = flagRHSuffisant;
    }

    public Boolean isFlagVegetarien() {
        return flagVegetarien;
    }

    public void setFlagVegetarien(Boolean flagVegetarien) {
        this.flagVegetarien = flagVegetarien;
    }

    public Boolean isFlagVegetalien() {
        return flagVegetalien;
    }

    public void setFlagVegetalien(Boolean flagVegetalien) {
        this.flagVegetalien = flagVegetalien;
    }

    public Boolean isFlagItemSansEtiquette() {
        return flagItemSansEtiquette;
    }

    public void setFlagItemSansEtiquette(Boolean flagItemSansEtiquette) {
        this.flagItemSansEtiquette = flagItemSansEtiquette;
    }

    public Boolean isFlagItemDate() {
        return flagItemDate;
    }

    public void setFlagItemDate(Boolean flagItemDate) {
        this.flagItemDate = flagItemDate;
    }

    public Boolean isFlagHalal() {
        return flagHalal;
    }

    public void setFlagHalal(Boolean flagHalal) {
        this.flagHalal = flagHalal;
    }

    public Boolean isFlagKasher() {
        return flagKasher;
    }

    public void setFlagKasher(Boolean flagKasher) {
        this.flagKasher = flagKasher;
    }

    public String getBoiteSuggestion() {
        return boiteSuggestion;
    }

    public void setBoiteSuggestion(String boiteSuggestion) {
        this.boiteSuggestion = boiteSuggestion;
    }

    public String getAutreRessource() {
        return autreRessource;
    }

    public void setAutreRessource(String autreRessource) {
        this.autreRessource = autreRessource;
    }

    public Long getDistanceVehId() {
        return distanceVehId;
    }

    public void setDistanceVehId(Long distanceVehId) {
        this.distanceVehId = distanceVehId;
    }

    public Long getOrganismeId() {
        return organismeId;
    }

    public void setOrganismeId(Long organismeId) {
        this.organismeId = organismeId;
    }

    public Long getDistanceVeloId() {
        return distanceVeloId;
    }

    public void setDistanceVeloId(Long distanceVeloId) {
        this.distanceVeloId = distanceVeloId;
    }

    public Long getEtatFrigoId() {
        return etatFrigoId;
    }

    public void setEtatFrigoId(Long etatFrigoId) {
        this.etatFrigoId = etatFrigoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DemandeDTO)) {
            return false;
        }

        return id != null && id.equals(((DemandeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DemandeDTO{" +
            "id=" + getId() +
            ", idDemande=" + getIdDemande() +
            ", frequence='" + getFrequence() + "'" +
            ", flagTypeDepDepannageUrgence='" + isFlagTypeDepDepannageUrgence() + "'" +
            ", flagTypeDepDepannageRegulier='" + isFlagTypeDepDepannageRegulier() + "'" +
            ", flagTypeDepPopoteRoulante='" + isFlagTypeDepPopoteRoulante() + "'" +
            ", flagTypeDepMarcheAbordable='" + isFlagTypeDepMarcheAbordable() + "'" +
            ", flagTypeDepEntraideAlimentaire='" + isFlagTypeDepEntraideAlimentaire() + "'" +
            ", flagTypeDepFrigoPartage='" + isFlagTypeDepFrigoPartage() + "'" +
            ", flagTypeDepAutre='" + isFlagTypeDepAutre() + "'" +
            ", besoinTechnique='" + getBesoinTechnique() + "'" +
            ", besoinUrgent='" + getBesoinUrgent() + "'" +
            ", flagCamionRegrigere='" + isFlagCamionRegrigere() + "'" +
            ", flagPasCongelateur='" + isFlagPasCongelateur() + "'" +
            ", flagCongelateurInd='" + isFlagCongelateurInd() + "'" +
            ", flagCongelateurPlainPied='" + isFlagCongelateurPlainPied() + "'" +
            ", flagCongelateurResidentiel='" + isFlagCongelateurResidentiel() + "'" +
            ", flagPasRefrigerateur='" + isFlagPasRefrigerateur() + "'" +
            ", flagRefrigerateurInd='" + isFlagRefrigerateurInd() + "'" +
            ", flagRefrigerateurPlainPied='" + isFlagRefrigerateurPlainPied() + "'" +
            ", flagRefrigerateurResidentiel='" + isFlagRefrigerateurResidentiel() + "'" +
            ", flagAccesGlaciere='" + isFlagAccesGlaciere() + "'" +
            ", flagAccesCuisine='" + isFlagAccesCuisine() + "'" +
            ", flagAccesVehicule='" + isFlagAccesVehicule() + "'" +
            ", dateInspection='" + getDateInspection() + "'" +
            ", nombreBeneficiaire=" + getNombreBeneficiaire() +
            ", prcAugmentationNbBenef=" + getPrcAugmentationNbBenef() +
            ", prcRHPerdue=" + getPrcRHPerdue() +
            ", prcBenevolePerdu=" + getPrcBenevolePerdu() +
            ", flagDenreeSuffisante='" + isFlagDenreeSuffisante() + "'" +
            ", flagRHSuffisant='" + isFlagRHSuffisant() + "'" +
            ", flagVegetarien='" + isFlagVegetarien() + "'" +
            ", flagVegetalien='" + isFlagVegetalien() + "'" +
            ", flagItemSansEtiquette='" + isFlagItemSansEtiquette() + "'" +
            ", flagItemDate='" + isFlagItemDate() + "'" +
            ", flagHalal='" + isFlagHalal() + "'" +
            ", flagKasher='" + isFlagKasher() + "'" +
            ", boiteSuggestion='" + getBoiteSuggestion() + "'" +
            ", autreRessource='" + getAutreRessource() + "'" +
            ", distanceVehId=" + getDistanceVehId() +
            ", organismeId=" + getOrganismeId() +
            ", distanceVeloId=" + getDistanceVeloId() +
            ", etatFrigoId=" + getEtatFrigoId() +
            "}";
    }
}
