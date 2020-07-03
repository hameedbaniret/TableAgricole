import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TerritoireComponentsPage, TerritoireDeleteDialog, TerritoireUpdatePage } from './territoire.page-object';

const expect = chai.expect;

describe('Territoire e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let territoireComponentsPage: TerritoireComponentsPage;
  let territoireUpdatePage: TerritoireUpdatePage;
  let territoireDeleteDialog: TerritoireDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Territoires', async () => {
    await navBarPage.goToEntity('territoire');
    territoireComponentsPage = new TerritoireComponentsPage();
    await browser.wait(ec.visibilityOf(territoireComponentsPage.title), 5000);
    expect(await territoireComponentsPage.getTitle()).to.eq('tableAgricoleApp.territoire.home.title');
    await browser.wait(ec.or(ec.visibilityOf(territoireComponentsPage.entities), ec.visibilityOf(territoireComponentsPage.noResult)), 1000);
  });

  it('should load create Territoire page', async () => {
    await territoireComponentsPage.clickOnCreateButton();
    territoireUpdatePage = new TerritoireUpdatePage();
    expect(await territoireUpdatePage.getPageTitle()).to.eq('tableAgricoleApp.territoire.home.createOrEditLabel');
    await territoireUpdatePage.cancel();
  });

  it('should create and save Territoires', async () => {
    const nbButtonsBeforeCreate = await territoireComponentsPage.countDeleteButtons();

    await territoireComponentsPage.clickOnCreateButton();

    await promise.all([territoireUpdatePage.setIdTerritoireInput('5'), territoireUpdatePage.setDscTerritoireInput('dscTerritoire')]);

    expect(await territoireUpdatePage.getIdTerritoireInput()).to.eq('5', 'Expected idTerritoire value to be equals to 5');
    expect(await territoireUpdatePage.getDscTerritoireInput()).to.eq(
      'dscTerritoire',
      'Expected DscTerritoire value to be equals to dscTerritoire'
    );

    await territoireUpdatePage.save();
    expect(await territoireUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await territoireComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Territoire', async () => {
    const nbButtonsBeforeDelete = await territoireComponentsPage.countDeleteButtons();
    await territoireComponentsPage.clickOnLastDeleteButton();

    territoireDeleteDialog = new TerritoireDeleteDialog();
    expect(await territoireDeleteDialog.getDialogTitle()).to.eq('tableAgricoleApp.territoire.delete.question');
    await territoireDeleteDialog.clickOnConfirmButton();

    expect(await territoireComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
