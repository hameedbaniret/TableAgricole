import { Moment } from 'moment';
import { IBesoin } from 'app/shared/model/besoin.model';
import { Frequence } from 'app/shared/model/enumerations/frequence.model';

export interface IDemande {
  id?: number;
  idDemande?: number;
  frequence?: Frequence;
  flagTypeDepDepannageUrgence?: boolean;
  flagTypeDepDepannageRegulier?: boolean;
  flagTypeDepPopoteRoulante?: boolean;
  flagTypeDepMarcheAbordable?: boolean;
  flagTypeDepEntraideAlimentaire?: boolean;
  flagTypeDepFrigoPartage?: boolean;
  flagTypeDepAutre?: boolean;
  besoinTechnique?: string;
  besoinUrgent?: string;
  flagCamionRegrigere?: boolean;
  flagPasCongelateur?: boolean;
  flagCongelateurInd?: boolean;
  flagCongelateurPlainPied?: boolean;
  flagCongelateurResidentiel?: boolean;
  flagPasRefrigerateur?: boolean;
  flagRefrigerateurInd?: boolean;
  flagRefrigerateurPlainPied?: boolean;
  flagRefrigerateurResidentiel?: boolean;
  flagAccesGlaciere?: boolean;
  flagAccesCuisine?: boolean;
  flagAccesVehicule?: boolean;
  dateInspection?: Moment;
  nombreBeneficiaire?: number;
  prcAugmentationNbBenef?: number;
  prcRHPerdue?: number;
  prcBenevolePerdu?: number;
  flagDenreeSuffisante?: boolean;
  flagRHSuffisant?: boolean;
  flagVegetarien?: boolean;
  flagVegetalien?: boolean;
  flagItemSansEtiquette?: boolean;
  flagItemDate?: boolean;
  flagHalal?: boolean;
  flagKasher?: boolean;
  boiteSuggestion?: string;
  autreRessource?: string;
  besoins?: IBesoin[];
  distanceVehId?: number;
  organismeId?: number;
  distanceVeloId?: number;
  etatFrigoId?: number;
}

export class Demande implements IDemande {
  constructor(
    public id?: number,
    public idDemande?: number,
    public frequence?: Frequence,
    public flagTypeDepDepannageUrgence?: boolean,
    public flagTypeDepDepannageRegulier?: boolean,
    public flagTypeDepPopoteRoulante?: boolean,
    public flagTypeDepMarcheAbordable?: boolean,
    public flagTypeDepEntraideAlimentaire?: boolean,
    public flagTypeDepFrigoPartage?: boolean,
    public flagTypeDepAutre?: boolean,
    public besoinTechnique?: string,
    public besoinUrgent?: string,
    public flagCamionRegrigere?: boolean,
    public flagPasCongelateur?: boolean,
    public flagCongelateurInd?: boolean,
    public flagCongelateurPlainPied?: boolean,
    public flagCongelateurResidentiel?: boolean,
    public flagPasRefrigerateur?: boolean,
    public flagRefrigerateurInd?: boolean,
    public flagRefrigerateurPlainPied?: boolean,
    public flagRefrigerateurResidentiel?: boolean,
    public flagAccesGlaciere?: boolean,
    public flagAccesCuisine?: boolean,
    public flagAccesVehicule?: boolean,
    public dateInspection?: Moment,
    public nombreBeneficiaire?: number,
    public prcAugmentationNbBenef?: number,
    public prcRHPerdue?: number,
    public prcBenevolePerdu?: number,
    public flagDenreeSuffisante?: boolean,
    public flagRHSuffisant?: boolean,
    public flagVegetarien?: boolean,
    public flagVegetalien?: boolean,
    public flagItemSansEtiquette?: boolean,
    public flagItemDate?: boolean,
    public flagHalal?: boolean,
    public flagKasher?: boolean,
    public boiteSuggestion?: string,
    public autreRessource?: string,
    public besoins?: IBesoin[],
    public distanceVehId?: number,
    public organismeId?: number,
    public distanceVeloId?: number,
    public etatFrigoId?: number
  ) {
    this.flagTypeDepDepannageUrgence = this.flagTypeDepDepannageUrgence || false;
    this.flagTypeDepDepannageRegulier = this.flagTypeDepDepannageRegulier || false;
    this.flagTypeDepPopoteRoulante = this.flagTypeDepPopoteRoulante || false;
    this.flagTypeDepMarcheAbordable = this.flagTypeDepMarcheAbordable || false;
    this.flagTypeDepEntraideAlimentaire = this.flagTypeDepEntraideAlimentaire || false;
    this.flagTypeDepFrigoPartage = this.flagTypeDepFrigoPartage || false;
    this.flagTypeDepAutre = this.flagTypeDepAutre || false;
    this.flagCamionRegrigere = this.flagCamionRegrigere || false;
    this.flagPasCongelateur = this.flagPasCongelateur || false;
    this.flagCongelateurInd = this.flagCongelateurInd || false;
    this.flagCongelateurPlainPied = this.flagCongelateurPlainPied || false;
    this.flagCongelateurResidentiel = this.flagCongelateurResidentiel || false;
    this.flagPasRefrigerateur = this.flagPasRefrigerateur || false;
    this.flagRefrigerateurInd = this.flagRefrigerateurInd || false;
    this.flagRefrigerateurPlainPied = this.flagRefrigerateurPlainPied || false;
    this.flagRefrigerateurResidentiel = this.flagRefrigerateurResidentiel || false;
    this.flagAccesGlaciere = this.flagAccesGlaciere || false;
    this.flagAccesCuisine = this.flagAccesCuisine || false;
    this.flagAccesVehicule = this.flagAccesVehicule || false;
    this.flagDenreeSuffisante = this.flagDenreeSuffisante || false;
    this.flagRHSuffisant = this.flagRHSuffisant || false;
    this.flagVegetarien = this.flagVegetarien || false;
    this.flagVegetalien = this.flagVegetalien || false;
    this.flagItemSansEtiquette = this.flagItemSansEtiquette || false;
    this.flagItemDate = this.flagItemDate || false;
    this.flagHalal = this.flagHalal || false;
    this.flagKasher = this.flagKasher || false;
  }
}
