import { element, by, ElementFinder } from 'protractor';

export class DistanceVeloComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-distance-velo div table .btn-danger'));
  title = element.all(by.css('jhi-distance-velo div h2#page-heading span')).first();
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

export class DistanceVeloUpdatePage {
  pageTitle = element(by.id('jhi-distance-velo-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idDistanceVeloInput = element(by.id('field_idDistanceVelo'));
  dscDistanceVeloInput = element(by.id('field_dscDistanceVelo'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdDistanceVeloInput(idDistanceVelo: string): Promise<void> {
    await this.idDistanceVeloInput.sendKeys(idDistanceVelo);
  }

  async getIdDistanceVeloInput(): Promise<string> {
    return await this.idDistanceVeloInput.getAttribute('value');
  }

  async setDscDistanceVeloInput(dscDistanceVelo: string): Promise<void> {
    await this.dscDistanceVeloInput.sendKeys(dscDistanceVelo);
  }

  async getDscDistanceVeloInput(): Promise<string> {
    return await this.dscDistanceVeloInput.getAttribute('value');
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

export class DistanceVeloDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-distanceVelo-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-distanceVelo'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
