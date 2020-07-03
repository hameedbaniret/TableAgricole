package ca.angios.tableagricole.domain;

import ca.angios.tableagricole.domain.enumeration.Frequence;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Demande.
 */
@Entity
@Table(name = "demande")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Demande implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_demande")
    private Long idDemande;

    @Enumerated(EnumType.STRING)
    @Column(name = "frequence")
    private Frequence frequence;

    @Column(name = "flag_type_dep_depannage_urgence")
    private Boolean flagTypeDepDepannageUrgence;

    @Column(name = "flag_type_dep_depannage_regulier")
    private Boolean flagTypeDepDepannageRegulier;

    @Column(name = "flag_type_dep_popote_roulante")
    private Boolean flagTypeDepPopoteRoulante;

    @Column(name = "flag_type_dep_marche_abordable")
    private Boolean flagTypeDepMarcheAbordable;

    @Column(name = "flag_type_dep_entraide_alimentaire")
    private Boolean flagTypeDepEntraideAlimentaire;

    @Column(name = "flag_type_dep_frigo_partage")
    private Boolean flagTypeDepFrigoPartage;

    @Column(name = "flag_type_dep_autre")
    private Boolean flagTypeDepAutre;

    @Column(name = "besoin_technique")
    private String besoinTechnique;

    @Column(name = "besoin_urgent")
    private String besoinUrgent;

    @Column(name = "flag_camion_regrigere")
    private Boolean flagCamionRegrigere;

    @Column(name = "flag_pas_congelateur")
    private Boolean flagPasCongelateur;

    @Column(name = "flag_congelateur_ind")
    private Boolean flagCongelateurInd;

    @Column(name = "flag_congelateur_plain_pied")
    private Boolean flagCongelateurPlainPied;

    @Column(name = "flag_congelateur_residentiel")
    private Boolean flagCongelateurResidentiel;

    @Column(name = "flag_pas_refrigerateur")
    private Boolean flagPasRefrigerateur;

    @Column(name = "flag_refrigerateur_ind")
    private Boolean flagRefrigerateurInd;

    @Column(name = "flag_refrigerateur_plain_pied")
    private Boolean flagRefrigerateurPlainPied;

    @Column(name = "flag_refrigerateur_residentiel")
    private Boolean flagRefrigerateurResidentiel;

    @Column(name = "flag_acces_glaciere")
    private Boolean flagAccesGlaciere;

    @Column(name = "flag_acces_cuisine")
    private Boolean flagAccesCuisine;

    @Column(name = "flag_acces_vehicule")
    private Boolean flagAccesVehicule;

    @Column(name = "date_inspection")
    private Instant dateInspection;

    @Column(name = "nombre_beneficiaire")
    private Long nombreBeneficiaire;

    @Column(name = "prc_augmentation_nb_benef")
    private Long prcAugmentationNbBenef;

    @Column(name = "prc_rh_perdue")
    private Long prcRHPerdue;

    @Column(name = "prc_benevole_perdu")
    private Long prcBenevolePerdu;

    @Column(name = "flag_denree_suffisante")
    private Boolean flagDenreeSuffisante;

    @Column(name = "flag_rh_suffisant")
    private Boolean flagRHSuffisant;

    @Column(name = "flag_vegetarien")
    private Boolean flagVegetarien;

    @Column(name = "flag_vegetalien")
    private Boolean flagVegetalien;

    @Column(name = "flag_item_sans_etiquette")
    private Boolean flagItemSansEtiquette;

    @Column(name = "flag_item_date")
    private Boolean flagItemDate;

    @Column(name = "flag_halal")
    private Boolean flagHalal;

    @Column(name = "flag_kasher")
    private Boolean flagKasher;

    @Column(name = "boite_suggestion")
    private String boiteSuggestion;

    @Column(name = "autre_ressource")
    private String autreRessource;

    @OneToMany(mappedBy = "demande")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Besoin> besoins = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "demandes", allowSetters = true)
    private DistanceVeh distanceVeh;

    @ManyToOne
    @JsonIgnoreProperties(value = "demandes", allowSetters = true)
    private Organisme organisme;

    @ManyToOne
    @JsonIgnoreProperties(value = "demandes", allowSetters = true)
    private DistanceVelo distanceVelo;

    @ManyToOne
    @JsonIgnoreProperties(value = "demandes", allowSetters = true)
    private EtatFrigo etatFrigo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDemande() {
        return idDemande;
    }

    public Demande idDemande(Long idDemande) {
        this.idDemande = idDemande;
        return this;
    }

    public void setIdDemande(Long idDemande) {
        this.idDemande = idDemande;
    }

    public Frequence getFrequence() {
        return frequence;
    }

    public Demande frequence(Frequence frequence) {
        this.frequence = frequence;
        return this;
    }

    public void setFrequence(Frequence frequence) {
        this.frequence = frequence;
    }

    public Boolean isFlagTypeDepDepannageUrgence() {
        return flagTypeDepDepannageUrgence;
    }

    public Demande flagTypeDepDepannageUrgence(Boolean flagTypeDepDepannageUrgence) {
        this.flagTypeDepDepannageUrgence = flagTypeDepDepannageUrgence;
        return this;
    }

    public void setFlagTypeDepDepannageUrgence(Boolean flagTypeDepDepannageUrgence) {
        this.flagTypeDepDepannageUrgence = flagTypeDepDepannageUrgence;
    }

    public Boolean isFlagTypeDepDepannageRegulier() {
        return flagTypeDepDepannageRegulier;
    }

    public Demande flagTypeDepDepannageRegulier(Boolean flagTypeDepDepannageRegulier) {
        this.flagTypeDepDepannageRegulier = flagTypeDepDepannageRegulier;
        return this;
    }

    public void setFlagTypeDepDepannageRegulier(Boolean flagTypeDepDepannageRegulier) {
        this.flagTypeDepDepannageRegulier = flagTypeDepDepannageRegulier;
    }

    public Boolean isFlagTypeDepPopoteRoulante() {
        return flagTypeDepPopoteRoulante;
    }

    public Demande flagTypeDepPopoteRoulante(Boolean flagTypeDepPopoteRoulante) {
        this.flagTypeDepPopoteRoulante = flagTypeDepPopoteRoulante;
        return this;
    }

    public void setFlagTypeDepPopoteRoulante(Boolean flagTypeDepPopoteRoulante) {
        this.flagTypeDepPopoteRoulante = flagTypeDepPopoteRoulante;
    }

    public Boolean isFlagTypeDepMarcheAbordable() {
        return flagTypeDepMarcheAbordable;
    }

    public Demande flagTypeDepMarcheAbordable(Boolean flagTypeDepMarcheAbordable) {
        this.flagTypeDepMarcheAbordable = flagTypeDepMarcheAbordable;
        return this;
    }

    public void setFlagTypeDepMarcheAbordable(Boolean flagTypeDepMarcheAbordable) {
        this.flagTypeDepMarcheAbordable = flagTypeDepMarcheAbordable;
    }

    public Boolean isFlagTypeDepEntraideAlimentaire() {
        return flagTypeDepEntraideAlimentaire;
    }

    public Demande flagTypeDepEntraideAlimentaire(Boolean flagTypeDepEntraideAlimentaire) {
        this.flagTypeDepEntraideAlimentaire = flagTypeDepEntraideAlimentaire;
        return this;
    }

    public void setFlagTypeDepEntraideAlimentaire(Boolean flagTypeDepEntraideAlimentaire) {
        this.flagTypeDepEntraideAlimentaire = flagTypeDepEntraideAlimentaire;
    }

    public Boolean isFlagTypeDepFrigoPartage() {
        return flagTypeDepFrigoPartage;
    }

    public Demande flagTypeDepFrigoPartage(Boolean flagTypeDepFrigoPartage) {
        this.flagTypeDepFrigoPartage = flagTypeDepFrigoPartage;
        return this;
    }

    public void setFlagTypeDepFrigoPartage(Boolean flagTypeDepFrigoPartage) {
        this.flagTypeDepFrigoPartage = flagTypeDepFrigoPartage;
    }

    public Boolean isFlagTypeDepAutre() {
        return flagTypeDepAutre;
    }

    public Demande flagTypeDepAutre(Boolean flagTypeDepAutre) {
        this.flagTypeDepAutre = flagTypeDepAutre;
        return this;
    }

    public void setFlagTypeDepAutre(Boolean flagTypeDepAutre) {
        this.flagTypeDepAutre = flagTypeDepAutre;
    }

    public String getBesoinTechnique() {
        return besoinTechnique;
    }

    public Demande besoinTechnique(String besoinTechnique) {
        this.besoinTechnique = besoinTechnique;
        return this;
    }

    public void setBesoinTechnique(String besoinTechnique) {
        this.besoinTechnique = besoinTechnique;
    }

    public String getBesoinUrgent() {
        return besoinUrgent;
    }

    public Demande besoinUrgent(String besoinUrgent) {
        this.besoinUrgent = besoinUrgent;
        return this;
    }

    public void setBesoinUrgent(String besoinUrgent) {
        this.besoinUrgent = besoinUrgent;
    }

    public Boolean isFlagCamionRegrigere() {
        return flagCamionRegrigere;
    }

    public Demande flagCamionRegrigere(Boolean flagCamionRegrigere) {
        this.flagCamionRegrigere = flagCamionRegrigere;
        return this;
    }

    public void setFlagCamionRegrigere(Boolean flagCamionRegrigere) {
        this.flagCamionRegrigere = flagCamionRegrigere;
    }

    public Boolean isFlagPasCongelateur() {
        return flagPasCongelateur;
    }

    public Demande flagPasCongelateur(Boolean flagPasCongelateur) {
        this.flagPasCongelateur = flagPasCongelateur;
        return this;
    }

    public void setFlagPasCongelateur(Boolean flagPasCongelateur) {
        this.flagPasCongelateur = flagPasCongelateur;
    }

    public Boolean isFlagCongelateurInd() {
        return flagCongelateurInd;
    }

    public Demande flagCongelateurInd(Boolean flagCongelateurInd) {
        this.flagCongelateurInd = flagCongelateurInd;
        return this;
    }

    public void setFlagCongelateurInd(Boolean flagCongelateurInd) {
        this.flagCongelateurInd = flagCongelateurInd;
    }

    public Boolean isFlagCongelateurPlainPied() {
        return flagCongelateurPlainPied;
    }

    public Demande flagCongelateurPlainPied(Boolean flagCongelateurPlainPied) {
        this.flagCongelateurPlainPied = flagCongelateurPlainPied;
        return this;
    }

    public void setFlagCongelateurPlainPied(Boolean flagCongelateurPlainPied) {
        this.flagCongelateurPlainPied = flagCongelateurPlainPied;
    }

    public Boolean isFlagCongelateurResidentiel() {
        return flagCongelateurResidentiel;
    }

    public Demande flagCongelateurResidentiel(Boolean flagCongelateurResidentiel) {
        this.flagCongelateurResidentiel = flagCongelateurResidentiel;
        return this;
    }

    public void setFlagCongelateurResidentiel(Boolean flagCongelateurResidentiel) {
        this.flagCongelateurResidentiel = flagCongelateurResidentiel;
    }

    public Boolean isFlagPasRefrigerateur() {
        return flagPasRefrigerateur;
    }

    public Demande flagPasRefrigerateur(Boolean flagPasRefrigerateur) {
        this.flagPasRefrigerateur = flagPasRefrigerateur;
        return this;
    }

    public void setFlagPasRefrigerateur(Boolean flagPasRefrigerateur) {
        this.flagPasRefrigerateur = flagPasRefrigerateur;
    }

    public Boolean isFlagRefrigerateurInd() {
        return flagRefrigerateurInd;
    }

    public Demande flagRefrigerateurInd(Boolean flagRefrigerateurInd) {
        this.flagRefrigerateurInd = flagRefrigerateurInd;
        return this;
    }

    public void setFlagRefrigerateurInd(Boolean flagRefrigerateurInd) {
        this.flagRefrigerateurInd = flagRefrigerateurInd;
    }

    public Boolean isFlagRefrigerateurPlainPied() {
        return flagRefrigerateurPlainPied;
    }

    public Demande flagRefrigerateurPlainPied(Boolean flagRefrigerateurPlainPied) {
        this.flagRefrigerateurPlainPied = flagRefrigerateurPlainPied;
        return this;
    }

    public void setFlagRefrigerateurPlainPied(Boolean flagRefrigerateurPlainPied) {
        this.flagRefrigerateurPlainPied = flagRefrigerateurPlainPied;
    }

    public Boolean isFlagRefrigerateurResidentiel() {
        return flagRefrigerateurResidentiel;
    }

    public Demande flagRefrigerateurResidentiel(Boolean flagRefrigerateurResidentiel) {
        this.flagRefrigerateurResidentiel = flagRefrigerateurResidentiel;
        return this;
    }

    public void setFlagRefrigerateurResidentiel(Boolean flagRefrigerateurResidentiel) {
        this.flagRefrigerateurResidentiel = flagRefrigerateurResidentiel;
    }

    public Boolean isFlagAccesGlaciere() {
        return flagAccesGlaciere;
    }

    public Demande flagAccesGlaciere(Boolean flagAccesGlaciere) {
        this.flagAccesGlaciere = flagAccesGlaciere;
        return this;
    }

    public void setFlagAccesGlaciere(Boolean flagAccesGlaciere) {
        this.flagAccesGlaciere = flagAccesGlaciere;
    }

    public Boolean isFlagAccesCuisine() {
        return flagAccesCuisine;
    }

    public Demande flagAccesCuisine(Boolean flagAccesCuisine) {
        this.flagAccesCuisine = flagAccesCuisine;
        return this;
    }

    public void setFlagAccesCuisine(Boolean flagAccesCuisine) {
        this.flagAccesCuisine = flagAccesCuisine;
    }

    public Boolean isFlagAccesVehicule() {
        return flagAccesVehicule;
    }

    public Demande flagAccesVehicule(Boolean flagAccesVehicule) {
        this.flagAccesVehicule = flagAccesVehicule;
        return this;
    }

    public void setFlagAccesVehicule(Boolean flagAccesVehicule) {
        this.flagAccesVehicule = flagAccesVehicule;
    }

    public Instant getDateInspection() {
        return dateInspection;
    }

    public Demande dateInspection(Instant dateInspection) {
        this.dateInspection = dateInspection;
        return this;
    }

    public void setDateInspection(Instant dateInspection) {
        this.dateInspection = dateInspection;
    }

    public Long getNombreBeneficiaire() {
        return nombreBeneficiaire;
    }

    public Demande nombreBeneficiaire(Long nombreBeneficiaire) {
        this.nombreBeneficiaire = nombreBeneficiaire;
        return this;
    }

    public void setNombreBeneficiaire(Long nombreBeneficiaire) {
        this.nombreBeneficiaire = nombreBeneficiaire;
    }

    public Long getPrcAugmentationNbBenef() {
        return prcAugmentationNbBenef;
    }

    public Demande prcAugmentationNbBenef(Long prcAugmentationNbBenef) {
        this.prcAugmentationNbBenef = prcAugmentationNbBenef;
        return this;
    }

    public void setPrcAugmentationNbBenef(Long prcAugmentationNbBenef) {
        this.prcAugmentationNbBenef = prcAugmentationNbBenef;
    }

    public Long getPrcRHPerdue() {
        return prcRHPerdue;
    }

    public Demande prcRHPerdue(Long prcRHPerdue) {
        this.prcRHPerdue = prcRHPerdue;
        return this;
    }

    public void setPrcRHPerdue(Long prcRHPerdue) {
        this.prcRHPerdue = prcRHPerdue;
    }

    public Long getPrcBenevolePerdu() {
        return prcBenevolePerdu;
    }

    public Demande prcBenevolePerdu(Long prcBenevolePerdu) {
        this.prcBenevolePerdu = prcBenevolePerdu;
        return this;
    }

    public void setPrcBenevolePerdu(Long prcBenevolePerdu) {
        this.prcBenevolePerdu = prcBenevolePerdu;
    }

    public Boolean isFlagDenreeSuffisante() {
        return flagDenreeSuffisante;
    }

    public Demande flagDenreeSuffisante(Boolean flagDenreeSuffisante) {
        this.flagDenreeSuffisante = flagDenreeSuffisante;
        return this;
    }

    public void setFlagDenreeSuffisante(Boolean flagDenreeSuffisante) {
        this.flagDenreeSuffisante = flagDenreeSuffisante;
    }

    public Boolean isFlagRHSuffisant() {
        return flagRHSuffisant;
    }

    public Demande flagRHSuffisant(Boolean flagRHSuffisant) {
        this.flagRHSuffisant = flagRHSuffisant;
        return this;
    }

    public void setFlagRHSuffisant(Boolean flagRHSuffisant) {
        this.flagRHSuffisant = flagRHSuffisant;
    }

    public Boolean isFlagVegetarien() {
        return flagVegetarien;
    }

    public Demande flagVegetarien(Boolean flagVegetarien) {
        this.flagVegetarien = flagVegetarien;
        return this;
    }

    public void setFlagVegetarien(Boolean flagVegetarien) {
        this.flagVegetarien = flagVegetarien;
    }

    public Boolean isFlagVegetalien() {
        return flagVegetalien;
    }

    public Demande flagVegetalien(Boolean flagVegetalien) {
        this.flagVegetalien = flagVegetalien;
        return this;
    }

    public void setFlagVegetalien(Boolean flagVegetalien) {
        this.flagVegetalien = flagVegetalien;
    }

    public Boolean isFlagItemSansEtiquette() {
        return flagItemSansEtiquette;
    }

    public Demande flagItemSansEtiquette(Boolean flagItemSansEtiquette) {
        this.flagItemSansEtiquette = flagItemSansEtiquette;
        return this;
    }

    public void setFlagItemSansEtiquette(Boolean flagItemSansEtiquette) {
        this.flagItemSansEtiquette = flagItemSansEtiquette;
    }

    public Boolean isFlagItemDate() {
        return flagItemDate;
    }

    public Demande flagItemDate(Boolean flagItemDate) {
        this.flagItemDate = flagItemDate;
        return this;
    }

    public void setFlagItemDate(Boolean flagItemDate) {
        this.flagItemDate = flagItemDate;
    }

    public Boolean isFlagHalal() {
        return flagHalal;
    }

    public Demande flagHalal(Boolean flagHalal) {
        this.flagHalal = flagHalal;
        return this;
    }

    public void setFlagHalal(Boolean flagHalal) {
        this.flagHalal = flagHalal;
    }

    public Boolean isFlagKasher() {
        return flagKasher;
    }

    public Demande flagKasher(Boolean flagKasher) {
        this.flagKasher = flagKasher;
        return this;
    }

    public void setFlagKasher(Boolean flagKasher) {
        this.flagKasher = flagKasher;
    }

    public String getBoiteSuggestion() {
        return boiteSuggestion;
    }

    public Demande boiteSuggestion(String boiteSuggestion) {
        this.boiteSuggestion = boiteSuggestion;
        return this;
    }

    public void setBoiteSuggestion(String boiteSuggestion) {
        this.boiteSuggestion = boiteSuggestion;
    }

    public String getAutreRessource() {
        return autreRessource;
    }

    public Demande autreRessource(String autreRessource) {
        this.autreRessource = autreRessource;
        return this;
    }

    public void setAutreRessource(String autreRessource) {
        this.autreRessource = autreRessource;
    }

    public Set<Besoin> getBesoins() {
        return besoins;
    }

    public Demande besoins(Set<Besoin> besoins) {
        this.besoins = besoins;
        return this;
    }

    public Demande addBesoin(Besoin besoin) {
        this.besoins.add(besoin);
        besoin.setDemande(this);
        return this;
    }

    public Demande removeBesoin(Besoin besoin) {
        this.besoins.remove(besoin);
        besoin.setDemande(null);
        return this;
    }

    public void setBesoins(Set<Besoin> besoins) {
        this.besoins = besoins;
    }

    public DistanceVeh getDistanceVeh() {
        return distanceVeh;
    }

    public Demande distanceVeh(DistanceVeh distanceVeh) {
        this.distanceVeh = distanceVeh;
        return this;
    }

    public void setDistanceVeh(DistanceVeh distanceVeh) {
        this.distanceVeh = distanceVeh;
    }

    public Organisme getOrganisme() {
        return organisme;
    }

    public Demande organisme(Organisme organisme) {
        this.organisme = organisme;
        return this;
    }

    public void setOrganisme(Organisme organisme) {
        this.organisme = organisme;
    }

    public DistanceVelo getDistanceVelo() {
        return distanceVelo;
    }

    public Demande distanceVelo(DistanceVelo distanceVelo) {
        this.distanceVelo = distanceVelo;
        return this;
    }

    public void setDistanceVelo(DistanceVelo distanceVelo) {
        this.distanceVelo = distanceVelo;
    }

    public EtatFrigo getEtatFrigo() {
        return etatFrigo;
    }

    public Demande etatFrigo(EtatFrigo etatFrigo) {
        this.etatFrigo = etatFrigo;
        return this;
    }

    public void setEtatFrigo(EtatFrigo etatFrigo) {
        this.etatFrigo = etatFrigo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Demande)) {
            return false;
        }
        return id != null && id.equals(((Demande) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Demande{" +
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
            "}";
    }
}
