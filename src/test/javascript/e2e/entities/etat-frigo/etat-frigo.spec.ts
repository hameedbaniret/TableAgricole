import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EtatFrigoComponentsPage, EtatFrigoDeleteDialog, EtatFrigoUpdatePage } from './etat-frigo.page-object';

const expect = chai.expect;

describe('EtatFrigo e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let etatFrigoComponentsPage: EtatFrigoComponentsPage;
  let etatFrigoUpdatePage: EtatFrigoUpdatePage;
  let etatFrigoDeleteDialog: EtatFrigoDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load EtatFrigos', async () => {
    await navBarPage.goToEntity('etat-frigo');
    etatFrigoComponentsPage = new EtatFrigoComponentsPage();
    await browser.wait(ec.visibilityOf(etatFrigoComponentsPage.title), 5000);
    expect(await etatFrigoComponentsPage.getTitle()).to.eq('tableAgricoleApp.etatFrigo.home.title');
    await browser.wait(ec.or(ec.visibilityOf(etatFrigoComponentsPage.entities), ec.visibilityOf(etatFrigoComponentsPage.noResult)), 1000);
  });

  it('should load create EtatFrigo page', async () => {
    await etatFrigoComponentsPage.clickOnCreateButton();
    etatFrigoUpdatePage = new EtatFrigoUpdatePage();
    expect(await etatFrigoUpdatePage.getPageTitle()).to.eq('tableAgricoleApp.etatFrigo.home.createOrEditLabel');
    await etatFrigoUpdatePage.cancel();
  });

  it('should create and save EtatFrigos', async () => {
    const nbButtonsBeforeCreate = await etatFrigoComponentsPage.countDeleteButtons();

    await etatFrigoComponentsPage.clickOnCreateButton();

    await promise.all([etatFrigoUpdatePage.setIdEtatFrigoInput('5'), etatFrigoUpdatePage.setDscEtatFrigoInput('dscEtatFrigo')]);

    expect(await etatFrigoUpdatePage.getIdEtatFrigoInput()).to.eq('5', 'Expected idEtatFrigo value to be equals to 5');
    expect(await etatFrigoUpdatePage.getDscEtatFrigoInput()).to.eq(
      'dscEtatFrigo',
      'Expected DscEtatFrigo value to be equals to dscEtatFrigo'
    );

    await etatFrigoUpdatePage.save();
    expect(await etatFrigoUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await etatFrigoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last EtatFrigo', async () => {
    const nbButtonsBeforeDelete = await etatFrigoComponentsPage.countDeleteButtons();
    await etatFrigoComponentsPage.clickOnLastDeleteButton();

    etatFrigoDeleteDialog = new EtatFrigoDeleteDialog();
    expect(await etatFrigoDeleteDialog.getDialogTitle()).to.eq('tableAgricoleApp.etatFrigo.delete.question');
    await etatFrigoDeleteDialog.clickOnConfirmButton();

    expect(await etatFrigoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
