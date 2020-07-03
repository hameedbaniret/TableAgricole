import { element, by, ElementFinder } from 'protractor';

export class EtatFrigoComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-etat-frigo div table .btn-danger'));
  title = element.all(by.css('jhi-etat-frigo div h2#page-heading span')).first();
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

export class EtatFrigoUpdatePage {
  pageTitle = element(by.id('jhi-etat-frigo-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idEtatFrigoInput = element(by.id('field_idEtatFrigo'));
  dscEtatFrigoInput = element(by.id('field_dscEtatFrigo'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdEtatFrigoInput(idEtatFrigo: string): Promise<void> {
    await this.idEtatFrigoInput.sendKeys(idEtatFrigo);
  }

  async getIdEtatFrigoInput(): Promise<string> {
    return await this.idEtatFrigoInput.getAttribute('value');
  }

  async setDscEtatFrigoInput(dscEtatFrigo: string): Promise<void> {
    await this.dscEtatFrigoInput.sendKeys(dscEtatFrigo);
  }

  async getDscEtatFrigoInput(): Promise<string> {
    return await this.dscEtatFrigoInput.getAttribute('value');
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

export class EtatFrigoDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-etatFrigo-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-etatFrigo'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
