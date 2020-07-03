import { element, by, ElementFinder } from 'protractor';

export class TerritoireComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-territoire div table .btn-danger'));
  title = element.all(by.css('jhi-territoire div h2#page-heading span')).first();
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

export class TerritoireUpdatePage {
  pageTitle = element(by.id('jhi-territoire-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idTerritoireInput = element(by.id('field_idTerritoire'));
  dscTerritoireInput = element(by.id('field_dscTerritoire'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdTerritoireInput(idTerritoire: string): Promise<void> {
    await this.idTerritoireInput.sendKeys(idTerritoire);
  }

  async getIdTerritoireInput(): Promise<string> {
    return await this.idTerritoireInput.getAttribute('value');
  }

  async setDscTerritoireInput(dscTerritoire: string): Promise<void> {
    await this.dscTerritoireInput.sendKeys(dscTerritoire);
  }

  async getDscTerritoireInput(): Promise<string> {
    return await this.dscTerritoireInput.getAttribute('value');
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

export class TerritoireDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-territoire-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-territoire'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
