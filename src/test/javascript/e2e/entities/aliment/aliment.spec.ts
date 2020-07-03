import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { AlimentComponentsPage, AlimentDeleteDialog, AlimentUpdatePage } from './aliment.page-object';

const expect = chai.expect;

describe('Aliment e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let alimentComponentsPage: AlimentComponentsPage;
  let alimentUpdatePage: AlimentUpdatePage;
  let alimentDeleteDialog: AlimentDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Aliments', async () => {
    await navBarPage.goToEntity('aliment');
    alimentComponentsPage = new AlimentComponentsPage();
    await browser.wait(ec.visibilityOf(alimentComponentsPage.title), 5000);
    expect(await alimentComponentsPage.getTitle()).to.eq('tableAgricoleApp.aliment.home.title');
    await browser.wait(ec.or(ec.visibilityOf(alimentComponentsPage.entities), ec.visibilityOf(alimentComponentsPage.noResult)), 1000);
  });

  it('should load create Aliment page', async () => {
    await alimentComponentsPage.clickOnCreateButton();
    alimentUpdatePage = new AlimentUpdatePage();
    expect(await alimentUpdatePage.getPageTitle()).to.eq('tableAgricoleApp.aliment.home.createOrEditLabel');
    await alimentUpdatePage.cancel();
  });

  it('should create and save Aliments', async () => {
    const nbButtonsBeforeCreate = await alimentComponentsPage.countDeleteButtons();

    await alimentComponentsPage.clickOnCreateButton();

    await promise.all([
      alimentUpdatePage.setIdAlimentInput('5'),
      alimentUpdatePage.setDscAlimentInput('dscAliment'),
      alimentUpdatePage.typeAlimentSelectLastOption(),
    ]);

    expect(await alimentUpdatePage.getIdAlimentInput()).to.eq('5', 'Expected idAliment value to be equals to 5');
    expect(await alimentUpdatePage.getDscAlimentInput()).to.eq('dscAliment', 'Expected DscAliment value to be equals to dscAliment');

    await alimentUpdatePage.save();
    expect(await alimentUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await alimentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Aliment', async () => {
    const nbButtonsBeforeDelete = await alimentComponentsPage.countDeleteButtons();
    await alimentComponentsPage.clickOnLastDeleteButton();

    alimentDeleteDialog = new AlimentDeleteDialog();
    expect(await alimentDeleteDialog.getDialogTitle()).to.eq('tableAgricoleApp.aliment.delete.question');
    await alimentDeleteDialog.clickOnConfirmButton();

    expect(await alimentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
