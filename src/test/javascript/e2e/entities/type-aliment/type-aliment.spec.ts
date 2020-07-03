import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TypeAlimentComponentsPage, TypeAlimentDeleteDialog, TypeAlimentUpdatePage } from './type-aliment.page-object';

const expect = chai.expect;

describe('TypeAliment e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let typeAlimentComponentsPage: TypeAlimentComponentsPage;
  let typeAlimentUpdatePage: TypeAlimentUpdatePage;
  let typeAlimentDeleteDialog: TypeAlimentDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load TypeAliments', async () => {
    await navBarPage.goToEntity('type-aliment');
    typeAlimentComponentsPage = new TypeAlimentComponentsPage();
    await browser.wait(ec.visibilityOf(typeAlimentComponentsPage.title), 5000);
    expect(await typeAlimentComponentsPage.getTitle()).to.eq('tableAgricoleApp.typeAliment.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(typeAlimentComponentsPage.entities), ec.visibilityOf(typeAlimentComponentsPage.noResult)),
      1000
    );
  });

  it('should load create TypeAliment page', async () => {
    await typeAlimentComponentsPage.clickOnCreateButton();
    typeAlimentUpdatePage = new TypeAlimentUpdatePage();
    expect(await typeAlimentUpdatePage.getPageTitle()).to.eq('tableAgricoleApp.typeAliment.home.createOrEditLabel');
    await typeAlimentUpdatePage.cancel();
  });

  it('should create and save TypeAliments', async () => {
    const nbButtonsBeforeCreate = await typeAlimentComponentsPage.countDeleteButtons();

    await typeAlimentComponentsPage.clickOnCreateButton();

    await promise.all([typeAlimentUpdatePage.setIdTypeAlimentInput('5'), typeAlimentUpdatePage.setDscTypeAlimentInput('dscTypeAliment')]);

    expect(await typeAlimentUpdatePage.getIdTypeAlimentInput()).to.eq('5', 'Expected idTypeAliment value to be equals to 5');
    expect(await typeAlimentUpdatePage.getDscTypeAlimentInput()).to.eq(
      'dscTypeAliment',
      'Expected DscTypeAliment value to be equals to dscTypeAliment'
    );

    await typeAlimentUpdatePage.save();
    expect(await typeAlimentUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await typeAlimentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last TypeAliment', async () => {
    const nbButtonsBeforeDelete = await typeAlimentComponentsPage.countDeleteButtons();
    await typeAlimentComponentsPage.clickOnLastDeleteButton();

    typeAlimentDeleteDialog = new TypeAlimentDeleteDialog();
    expect(await typeAlimentDeleteDialog.getDialogTitle()).to.eq('tableAgricoleApp.typeAliment.delete.question');
    await typeAlimentDeleteDialog.clickOnConfirmButton();

    expect(await typeAlimentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
