import { element, by, ElementFinder } from 'protractor';

export class DistanceVehComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-distance-veh div table .btn-danger'));
  title = element.all(by.css('jhi-distance-veh div h2#page-heading span')).first();
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

export class DistanceVehUpdatePage {
  pageTitle = element(by.id('jhi-distance-veh-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idDistanceVehInput = element(by.id('field_idDistanceVeh'));
  dscDistanceVehInput = element(by.id('field_dscDistanceVeh'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdDistanceVehInput(idDistanceVeh: string): Promise<void> {
    await this.idDistanceVehInput.sendKeys(idDistanceVeh);
  }

  async getIdDistanceVehInput(): Promise<string> {
    return await this.idDistanceVehInput.getAttribute('value');
  }

  async setDscDistanceVehInput(dscDistanceVeh: string): Promise<void> {
    await this.dscDistanceVehInput.sendKeys(dscDistanceVeh);
  }

  async getDscDistanceVehInput(): Promise<string> {
    return await this.dscDistanceVehInput.getAttribute('value');
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

export class DistanceVehDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-distanceVeh-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-distanceVeh'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
