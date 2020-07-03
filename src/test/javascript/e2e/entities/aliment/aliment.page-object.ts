import { element, by, ElementFinder } from 'protractor';

export class AlimentComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-aliment div table .btn-danger'));
  title = element.all(by.css('jhi-aliment div h2#page-heading span')).first();
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

export class AlimentUpdatePage {
  pageTitle = element(by.id('jhi-aliment-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idAlimentInput = element(by.id('field_idAliment'));
  dscAlimentInput = element(by.id('field_dscAliment'));

  typeAlimentSelect = element(by.id('field_typeAliment'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdAlimentInput(idAliment: string): Promise<void> {
    await this.idAlimentInput.sendKeys(idAliment);
  }

  async getIdAlimentInput(): Promise<string> {
    return await this.idAlimentInput.getAttribute('value');
  }

  async setDscAlimentInput(dscAliment: string): Promise<void> {
    await this.dscAlimentInput.sendKeys(dscAliment);
  }

  async getDscAlimentInput(): Promise<string> {
    return await this.dscAlimentInput.getAttribute('value');
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

export class AlimentDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-aliment-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-aliment'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
