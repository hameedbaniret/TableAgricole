import { element, by, ElementFinder } from 'protractor';

export class DemandeComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-demande div table .btn-danger'));
  title = element.all(by.css('jhi-demande div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class DemandeUpdatePage {
  pageTitle = element(by.id('jhi-demande-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idDemandeInput = element(by.id('field_idDemande'));
  frequenceSelect = element(by.id('field_frequence'));
  flagTypeDepDepannageUrgenceInput = element(by.id('field_flagTypeDepDepannageUrgence'));
  flagTypeDepDepannageRegulierInput = element(by.id('field_flagTypeDepDepannageRegulier'));
  flagTypeDepPopoteRoulanteInput = element(by.id('field_flagTypeDepPopoteRoulante'));
  flagTypeDepMarcheAbordableInput = element(by.id('field_flagTypeDepMarcheAbordable'));
  flagTypeDepEntraideAlimentaireInput = element(by.id('field_flagTypeDepEntraideAlimentaire'));
  flagTypeDepFrigoPartageInput = element(by.id('field_flagTypeDepFrigoPartage'));
  flagTypeDepAutreInput = element(by.id('field_flagTypeDepAutre'));
  besoinTechniqueInput = element(by.id('field_besoinTechnique'));
  besoinUrgentInput = element(by.id('field_besoinUrgent'));
  flagCamionRegrigereInput = element(by.id('field_flagCamionRegrigere'));
  flagPasCongelateurInput = element(by.id('field_flagPasCongelateur'));
  flagCongelateurIndInput = element(by.id('field_flagCongelateurInd'));
  flagCongelateurPlainPiedInput = element(by.id('field_flagCongelateurPlainPied'));
  flagCongelateurResidentielInput = element(by.id('field_flagCongelateurResidentiel'));
  flagPasRefrigerateurInput = element(by.id('field_flagPasRefrigerateur'));
  flagRefrigerateurIndInput = element(by.id('field_flagRefrigerateurInd'));
  flagRefrigerateurPlainPiedInput = element(by.id('field_flagRefrigerateurPlainPied'));
  flagRefrigerateurResidentielInput = element(by.id('field_flagRefrigerateurResidentiel'));
  flagAccesGlaciereInput = element(by.id('field_flagAccesGlaciere'));
  flagAccesCuisineInput = element(by.id('field_flagAccesCuisine'));
  flagAccesVehiculeInput = element(by.id('field_flagAccesVehicule'));
  dateInspectionInput = element(by.id('field_dateInspection'));
  nombreBeneficiaireInput = element(by.id('field_nombreBeneficiaire'));
  prcAugmentationNbBenefInput = element(by.id('field_prcAugmentationNbBenef'));
  prcRHPerdueInput = element(by.id('field_prcRHPerdue'));
  prcBenevolePerduInput = element(by.id('field_prcBenevolePerdu'));
  flagDenreeSuffisanteInput = element(by.id('field_flagDenreeSuffisante'));
  flagRHSuffisantInput = element(by.id('field_flagRHSuffisant'));
  flagVegetarienInput = element(by.id('field_flagVegetarien'));
  flagVegetalienInput = element(by.id('field_flagVegetalien'));
  flagItemSansEtiquetteInput = element(by.id('field_flagItemSansEtiquette'));
  flagItemDateInput = element(by.id('field_flagItemDate'));
  flagHalalInput = element(by.id('field_flagHalal'));
  flagKasherInput = element(by.id('field_flagKasher'));
  boiteSuggestionInput = element(by.id('field_boiteSuggestion'));
  autreRessourceInput = element(by.id('field_autreRessource'));

  distanceVehSelect = element(by.id('field_distanceVeh'));
  organismeSelect = element(by.id('field_organisme'));
  distanceVeloSelect = element(by.id('field_distanceVelo'));
  etatFrigoSelect = element(by.id('field_etatFrigo'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdDemandeInput(idDemande: string): Promise<void> {
    await this.idDemandeInput.sendKeys(idDemande);
  }

  async getIdDemandeInput(): Promise<string> {
    return await this.idDemandeInput.getAttribute('value');
  }

  async setFrequenceSelect(frequence: string): Promise<void> {
    await this.frequenceSelect.sendKeys(frequence);
  }

  async getFrequenceSelect(): Promise<string> {
    return await this.frequenceSelect.element(by.css('option:checked')).getText();
  }

  async frequenceSelectLastOption(): Promise<void> {
    await this.frequenceSelect.all(by.tagName('option')).last().click();
  }

  getFlagTypeDepDepannageUrgenceInput(): ElementFinder {
    return this.flagTypeDepDepannageUrgenceInput;
  }

  getFlagTypeDepDepannageRegulierInput(): ElementFinder {
    return this.flagTypeDepDepannageRegulierInput;
  }

  getFlagTypeDepPopoteRoulanteInput(): ElementFinder {
    return this.flagTypeDepPopoteRoulanteInput;
  }

  getFlagTypeDepMarcheAbordableInput(): ElementFinder {
    return this.flagTypeDepMarcheAbordableInput;
  }

  getFlagTypeDepEntraideAlimentaireInput(): ElementFinder {
    return this.flagTypeDepEntraideAlimentaireInput;
  }

  getFlagTypeDepFrigoPartageInput(): ElementFinder {
    return this.flagTypeDepFrigoPartageInput;
  }

  getFlagTypeDepAutreInput(): ElementFinder {
    return this.flagTypeDepAutreInput;
  }

  async setBesoinTechniqueInput(besoinTechnique: string): Promise<void> {
    await this.besoinTechniqueInput.sendKeys(besoinTechnique);
  }

  async getBesoinTechniqueInput(): Promise<string> {
    return await this.besoinTechniqueInput.getAttribute('value');
  }

  async setBesoinUrgentInput(besoinUrgent: string): Promise<void> {
    await this.besoinUrgentInput.sendKeys(besoinUrgent);
  }

  async getBesoinUrgentInput(): Promise<string> {
    return await this.besoinUrgentInput.getAttribute('value');
  }

  getFlagCamionRegrigereInput(): ElementFinder {
    return this.flagCamionRegrigereInput;
  }

  getFlagPasCongelateurInput(): ElementFinder {
    return this.flagPasCongelateurInput;
  }

  getFlagCongelateurIndInput(): ElementFinder {
    return this.flagCongelateurIndInput;
  }

  getFlagCongelateurPlainPiedInput(): ElementFinder {
    return this.flagCongelateurPlainPiedInput;
  }

  getFlagCongelateurResidentielInput(): ElementFinder {
    return this.flagCongelateurResidentielInput;
  }

  getFlagPasRefrigerateurInput(): ElementFinder {
    return this.flagPasRefrigerateurInput;
  }

  getFlagRefrigerateurIndInput(): ElementFinder {
    return this.flagRefrigerateurIndInput;
  }

  getFlagRefrigerateurPlainPiedInput(): ElementFinder {
    return this.flagRefrigerateurPlainPiedInput;
  }

  getFlagRefrigerateurResidentielInput(): ElementFinder {
    return this.flagRefrigerateurResidentielInput;
  }

  getFlagAccesGlaciereInput(): ElementFinder {
    return this.flagAccesGlaciereInput;
  }

  getFlagAccesCuisineInput(): ElementFinder {
    return this.flagAccesCuisineInput;
  }

  getFlagAccesVehiculeInput(): ElementFinder {
    return this.flagAccesVehiculeInput;
  }

  async setDateInspectionInput(dateInspection: string): Promise<void> {
    await this.dateInspectionInput.sendKeys(dateInspection);
  }

  async getDateInspectionInput(): Promise<string> {
    return await this.dateInspectionInput.getAttribute('value');
  }

  async setNombreBeneficiaireInput(nombreBeneficiaire: string): Promise<void> {
    await this.nombreBeneficiaireInput.sendKeys(nombreBeneficiaire);
  }

  async getNombreBeneficiaireInput(): Promise<string> {
    return await this.nombreBeneficiaireInput.getAttribute('value');
  }

  async setPrcAugmentationNbBenefInput(prcAugmentationNbBenef: string): Promise<void> {
    await this.prcAugmentationNbBenefInput.sendKeys(prcAugmentationNbBenef);
  }

  async getPrcAugmentationNbBenefInput(): Promise<string> {
    return await this.prcAugmentationNbBenefInput.getAttribute('value');
  }

  async setPrcRHPerdueInput(prcRHPerdue: string): Promise<void> {
    await this.prcRHPerdueInput.sendKeys(prcRHPerdue);
  }

  async getPrcRHPerdueInput(): Promise<string> {
    return await this.prcRHPerdueInput.getAttribute('value');
  }

  async setPrcBenevolePerduInput(prcBenevolePerdu: string): Promise<void> {
    await this.prcBenevolePerduInput.sendKeys(prcBenevolePerdu);
  }

  async getPrcBenevolePerduInput(): Promise<string> {
    return await this.prcBenevolePerduInput.getAttribute('value');
  }

  getFlagDenreeSuffisanteInput(): ElementFinder {
    return this.flagDenreeSuffisanteInput;
  }

  getFlagRHSuffisantInput(): ElementFinder {
    return this.flagRHSuffisantInput;
  }

  getFlagVegetarienInput(): ElementFinder {
    return this.flagVegetarienInput;
  }

  getFlagVegetalienInput(): ElementFinder {
    return this.flagVegetalienInput;
  }

  getFlagItemSansEtiquetteInput(): ElementFinder {
    return this.flagItemSansEtiquetteInput;
  }

  getFlagItemDateInput(): ElementFinder {
    return this.flagItemDateInput;
  }

  getFlagHalalInput(): ElementFinder {
    return this.flagHalalInput;
  }

  getFlagKasherInput(): ElementFinder {
    return this.flagKasherInput;
  }

  async setBoiteSuggestionInput(boiteSuggestion: string): Promise<void> {
    await this.boiteSuggestionInput.sendKeys(boiteSuggestion);
  }

  async getBoiteSuggestionInput(): Promise<string> {
    return await this.boiteSuggestionInput.getAttribute('value');
  }

  async setAutreRessourceInput(autreRessource: string): Promise<void> {
    await this.autreRessourceInput.sendKeys(autreRessource);
  }

  async getAutreRessourceInput(): Promise<string> {
    return await this.autreRessourceInput.getAttribute('value');
  }

  async distanceVehSelectLastOption(): Promise<void> {
    await this.distanceVehSelect.all(by.tagName('option')).last().click();
  }

  async distanceVehSelectOption(option: string): Promise<void> {
    await this.distanceVehSelect.sendKeys(option);
  }

  getDistanceVehSelect(): ElementFinder {
    return this.distanceVehSelect;
  }

  async getDistanceVehSelectedOption(): Promise<string> {
    return await this.distanceVehSelect.element(by.css('option:checked')).getText();
  }

  async organismeSelectLastOption(): Promise<void> {
    await this.organismeSelect.all(by.tagName('option')).last().click();
  }

  async organismeSelectOption(option: string): Promise<void> {
    await this.organismeSelect.sendKeys(option);
  }

  getOrganismeSelect(): ElementFinder {
    return this.organismeSelect;
  }

  async getOrganismeSelectedOption(): Promise<string> {
    return await this.organismeSelect.element(by.css('option:checked')).getText();
  }

  async distanceVeloSelectLastOption(): Promise<void> {
    await this.distanceVeloSelect.all(by.tagName('option')).last().click();
  }

  async distanceVeloSelectOption(option: string): Promise<void> {
    await this.distanceVeloSelect.sendKeys(option);
  }

  getDistanceVeloSelect(): ElementFinder {
    return this.distanceVeloSelect;
  }

  async getDistanceVeloSelectedOption(): Promise<string> {
    return await this.distanceVeloSelect.element(by.css('option:checked')).getText();
  }

  async etatFrigoSelectLastOption(): Promise<void> {
    await this.etatFrigoSelect.all(by.tagName('option')).last().click();
  }

  async etatFrigoSelectOption(option: string): Promise<void> {
    await this.etatFrigoSelect.sendKeys(option);
  }

  getEtatFrigoSelect(): ElementFinder {
    return this.etatFrigoSelect;
  }

  async getEtatFrigoSelectedOption(): Promise<string> {
    return await this.etatFrigoSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class DemandeDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-demande-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-demande'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
