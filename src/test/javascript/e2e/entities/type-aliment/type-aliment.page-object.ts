import { element, by, ElementFinder } from 'protractor';

export class TypeAlimentComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-type-aliment div table .btn-danger'));
  title = element.all(by.css('jhi-type-aliment div h2#page-heading span')).first();
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

export class TypeAlimentUpdatePage {
  pageTitle = element(by.id('jhi-type-aliment-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idTypeAlimentInput = element(by.id('field_idTypeAliment'));
  dscTypeAlimentInput = element(by.id('field_dscTypeAliment'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdTypeAlimentInput(idTypeAliment: string): Promise<void> {
    await this.idTypeAlimentInput.sendKeys(idTypeAliment);
  }

  async getIdTypeAlimentInput(): Promise<string> {
    return await this.idTypeAlimentInput.getAttribute('value');
  }

  async setDscTypeAlimentInput(dscTypeAliment: string): Promise<void> {
    await this.dscTypeAlimentInput.sendKeys(dscTypeAliment);
  }

  async getDscTypeAlimentInput(): Promise<string> {
    return await this.dscTypeAlimentInput.getAttribute('value');
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

export class TypeAlimentDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-typeAliment-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-typeAliment'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
