import { element, by, ElementFinder } from 'protractor';

export class BesoinComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-besoin div table .btn-danger'));
  title = element.all(by.css('jhi-besoin div h2#page-heading span')).first();
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

export class BesoinUpdatePage {
  pageTitle = element(by.id('jhi-besoin-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idBesoinInput = element(by.id('field_idBesoin'));
  besoinFrequenceSelect = element(by.id('field_besoinFrequence'));
  typeBesoinSelect = element(by.id('field_typeBesoin'));
  quantiteInput = element(by.id('field_quantite'));
  datePeremptionInput = element(by.id('field_datePeremption'));
  dateBesoinInput = element(by.id('field_dateBesoin'));
  titreEmploiInput = element(by.id('field_titreEmploi'));
  tachePrincipaleInput = element(by.id('field_tachePrincipale'));
  nbHeureInput = element(by.id('field_nbHeure'));
  dureeContratInput = element(by.id('field_dureeContrat'));
  dateEntreeInput = element(by.id('field_dateEntree'));
  nbBeneficiaireInput = element(by.id('field_nbBeneficiaire'));
  serviceSouhaiteInput = element(by.id('field_serviceSouhaite'));
  dateRecuperationInput = element(by.id('field_dateRecuperation'));

  typeAlimentSelect = element(by.id('field_typeAliment'));
  alimentSelect = element(by.id('field_aliment'));
  demandeSelect = element(by.id('field_demande'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdBesoinInput(idBesoin: string): Promise<void> {
    await this.idBesoinInput.sendKeys(idBesoin);
  }

  async getIdBesoinInput(): Promise<string> {
    return await this.idBesoinInput.getAttribute('value');
  }

  async setBesoinFrequenceSelect(besoinFrequence: string): Promise<void> {
    await this.besoinFrequenceSelect.sendKeys(besoinFrequence);
  }

  async getBesoinFrequenceSelect(): Promise<string> {
    return await this.besoinFrequenceSelect.element(by.css('option:checked')).getText();
  }

  async besoinFrequenceSelectLastOption(): Promise<void> {
    await this.besoinFrequenceSelect.all(by.tagName('option')).last().click();
  }

  async setTypeBesoinSelect(typeBesoin: string): Promise<void> {
    await this.typeBesoinSelect.sendKeys(typeBesoin);
  }

  async getTypeBesoinSelect(): Promise<string> {
    return await this.typeBesoinSelect.element(by.css('option:checked')).getText();
  }

  async typeBesoinSelectLastOption(): Promise<void> {
    await this.typeBesoinSelect.all(by.tagName('option')).last().click();
  }

  async setQuantiteInput(quantite: string): Promise<void> {
    await this.quantiteInput.sendKeys(quantite);
  }

  async getQuantiteInput(): Promise<string> {
    return await this.quantiteInput.getAttribute('value');
  }

  async setDatePeremptionInput(datePeremption: string): Promise<void> {
    await this.datePeremptionInput.sendKeys(datePeremption);
  }

  async getDatePeremptionInput(): Promise<string> {
    return await this.datePeremptionInput.getAttribute('value');
  }

  async setDateBesoinInput(dateBesoin: string): Promise<void> {
    await this.dateBesoinInput.sendKeys(dateBesoin);
  }

  async getDateBesoinInput(): Promise<string> {
    return await this.dateBesoinInput.getAttribute('value');
  }

  async setTitreEmploiInput(titreEmploi: string): Promise<void> {
    await this.titreEmploiInput.sendKeys(titreEmploi);
  }

  async getTitreEmploiInput(): Promise<string> {
    return await this.titreEmploiInput.getAttribute('value');
  }

  async setTachePrincipaleInput(tachePrincipale: string): Promise<void> {
    await this.tachePrincipaleInput.sendKeys(tachePrincipale);
  }

  async getTachePrincipaleInput(): Promise<string> {
    return await this.tachePrincipaleInput.getAttribute('value');
  }

  async setNbHeureInput(nbHeure: string): Promise<void> {
    await this.nbHeureInput.sendKeys(nbHeure);
  }

  async getNbHeureInput(): Promise<string> {
    return await this.nbHeureInput.getAttribute('value');
  }

  async setDureeContratInput(dureeContrat: string): Promise<void> {
    await this.dureeContratInput.sendKeys(dureeContrat);
  }

  async getDureeContratInput(): Promise<string> {
    return await this.dureeContratInput.getAttribute('value');
  }

  async setDateEntreeInput(dateEntree: string): Promise<void> {
    await this.dateEntreeInput.sendKeys(dateEntree);
  }

  async getDateEntreeInput(): Promise<string> {
    return await this.dateEntreeInput.getAttribute('value');
  }

  async setNbBeneficiaireInput(nbBeneficiaire: string): Promise<void> {
    await this.nbBeneficiaireInput.sendKeys(nbBeneficiaire);
  }

  async getNbBeneficiaireInput(): Promise<string> {
    return await this.nbBeneficiaireInput.getAttribute('value');
  }

  async setServiceSouhaiteInput(serviceSouhaite: string): Promise<void> {
    await this.serviceSouhaiteInput.sendKeys(serviceSouhaite);
  }

  async getServiceSouhaiteInput(): Promise<string> {
    return await this.serviceSouhaiteInput.getAttribute('value');
  }

  async setDateRecuperationInput(dateRecuperation: string): Promise<void> {
    await this.dateRecuperationInput.sendKeys(dateRecuperation);
  }

  async getDateRecuperationInput(): Promise<string> {
    return await this.dateRecuperationInput.getAttribute('value');
  }

  async typeAlimentSelectLastOption(): Promise<void> {
    await this.typeAlimentSelect.all(by.tagName('option')).last().click();
  }

  async typeAlimentSelectOption(option: string): Promise<void> {
    await this.typeAlimentSelect.sendKeys(option);
  }

  getTypeAlimentSelect(): ElementFinder {
    return this.typeAlimentSelect;
  }

  async getTypeAlimentSelectedOption(): Promise<string> {
    return await this.typeAlimentSelect.element(by.css('option:checked')).getText();
  }

  async alimentSelectLastOption(): Promise<void> {
    await this.alimentSelect.all(by.tagName('option')).last().click();
  }

  async alimentSelectOption(option: string): Promise<void> {
    await this.alimentSelect.sendKeys(option);
  }

  getAlimentSelect(): ElementFinder {
    return this.alimentSelect;
  }

  async getAlimentSelectedOption(): Promise<string> {
    return await this.alimentSelect.element(by.css('option:checked')).getText();
  }

  async demandeSelectLastOption(): Promise<void> {
    await this.demandeSelect.all(by.tagName('option')).last().click();
  }

  async demandeSelectOption(option: string): Promise<void> {
    await this.demandeSelect.sendKeys(option);
  }

  getDemandeSelect(): ElementFinder {
    return this.demandeSelect;
  }

  async getDemandeSelectedOption(): Promise<string> {
    return await this.demandeSelect.element(by.css('option:checked')).getText();
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

export class BesoinDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-besoin-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-besoin'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
