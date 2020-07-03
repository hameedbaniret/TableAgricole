import { element, by, ElementFinder } from 'protractor';

export class OrganismeComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-organisme div table .btn-danger'));
  title = element.all(by.css('jhi-organisme div h2#page-heading span')).first();
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

export class OrganismeUpdatePage {
  pageTitle = element(by.id('jhi-organisme-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idOrganismeInput = element(by.id('field_idOrganisme'));
  nomOrganismeInput = element(by.id('field_nomOrganisme'));
  adresseOrganismeInput = element(by.id('field_adresseOrganisme'));
  phoneOrganismeInput = element(by.id('field_phoneOrganisme'));
  courrielOrganismeInput = element(by.id('field_courrielOrganisme'));

  territoireSelect = element(by.id('field_territoire'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdOrganismeInput(idOrganisme: string): Promise<void> {
    await this.idOrganismeInput.sendKeys(idOrganisme);
  }

  async getIdOrganismeInput(): Promise<string> {
    return await this.idOrganismeInput.getAttribute('value');
  }

  async setNomOrganismeInput(nomOrganisme: string): Promise<void> {
    await this.nomOrganismeInput.sendKeys(nomOrganisme);
  }

  async getNomOrganismeInput(): Promise<string> {
    return await this.nomOrganismeInput.getAttribute('value');
  }

  async setAdresseOrganismeInput(adresseOrganisme: string): Promise<void> {
    await this.adresseOrganismeInput.sendKeys(adresseOrganisme);
  }

  async getAdresseOrganismeInput(): Promise<string> {
    return await this.adresseOrganismeInput.getAttribute('value');
  }

  async setPhoneOrganismeInput(phoneOrganisme: string): Promise<void> {
    await this.phoneOrganismeInput.sendKeys(phoneOrganisme);
  }

  async getPhoneOrganismeInput(): Promise<string> {
    return await this.phoneOrganismeInput.getAttribute('value');
  }

  async setCourrielOrganismeInput(courrielOrganisme: string): Promise<void> {
    await this.courrielOrganismeInput.sendKeys(courrielOrganisme);
  }

  async getCourrielOrganismeInput(): Promise<string> {
    return await this.courrielOrganismeInput.getAttribute('value');
  }

  async territoireSelectLastOption(): Promise<void> {
    await this.territoireSelect.all(by.tagName('option')).last().click();
  }

  async territoireSelectOption(option: string): Promise<void> {
    await this.territoireSelect.sendKeys(option);
  }

  getTerritoireSelect(): ElementFinder {
    return this.territoireSelect;
  }

  async getTerritoireSelectedOption(): Promise<string> {
    return await this.territoireSelect.element(by.css('option:checked')).getText();
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

export class OrganismeDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-organisme-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-organisme'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
