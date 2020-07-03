import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OrganismeComponentsPage, OrganismeDeleteDialog, OrganismeUpdatePage } from './organisme.page-object';

const expect = chai.expect;

describe('Organisme e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let organismeComponentsPage: OrganismeComponentsPage;
  let organismeUpdatePage: OrganismeUpdatePage;
  let organismeDeleteDialog: OrganismeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Organismes', async () => {
    await navBarPage.goToEntity('organisme');
    organismeComponentsPage = new OrganismeComponentsPage();
    await browser.wait(ec.visibilityOf(organismeComponentsPage.title), 5000);
    expect(await organismeComponentsPage.getTitle()).to.eq('tableAgricoleApp.organisme.home.title');
    await browser.wait(ec.or(ec.visibilityOf(organismeComponentsPage.entities), ec.visibilityOf(organismeComponentsPage.noResult)), 1000);
  });

  it('should load create Organisme page', async () => {
    await organismeComponentsPage.clickOnCreateButton();
    organismeUpdatePage = new OrganismeUpdatePage();
    expect(await organismeUpdatePage.getPageTitle()).to.eq('tableAgricoleApp.organisme.home.createOrEditLabel');
    await organismeUpdatePage.cancel();
  });

  it('should create and save Organismes', async () => {
    const nbButtonsBeforeCreate = await organismeComponentsPage.countDeleteButtons();

    await organismeComponentsPage.clickOnCreateButton();

    await promise.all([
      organismeUpdatePage.setIdOrganismeInput('5'),
      organismeUpdatePage.setNomOrganismeInput('nomOrganisme'),
      organismeUpdatePage.setAdresseOrganismeInput('adresseOrganisme'),
      organismeUpdatePage.setPhoneOrganismeInput('phoneOrganisme'),
      organismeUpdatePage.setCourrielOrganismeInput('courrielOrganisme'),
      organismeUpdatePage.territoireSelectLastOption(),
    ]);

    expect(await organismeUpdatePage.getIdOrganismeInput()).to.eq('5', 'Expected idOrganisme value to be equals to 5');
    expect(await organismeUpdatePage.getNomOrganismeInput()).to.eq(
      'nomOrganisme',
      'Expected NomOrganisme value to be equals to nomOrganisme'
    );
    expect(await organismeUpdatePage.getAdresseOrganismeInput()).to.eq(
      'adresseOrganisme',
      'Expected AdresseOrganisme value to be equals to adresseOrganisme'
    );
    expect(await organismeUpdatePage.getPhoneOrganismeInput()).to.eq(
      'phoneOrganisme',
      'Expected PhoneOrganisme value to be equals to phoneOrganisme'
    );
    expect(await organismeUpdatePage.getCourrielOrganismeInput()).to.eq(
      'courrielOrganisme',
      'Expected CourrielOrganisme value to be equals to courrielOrganisme'
    );

    await organismeUpdatePage.save();
    expect(await organismeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await organismeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Organisme', async () => {
    const nbButtonsBeforeDelete = await organismeComponentsPage.countDeleteButtons();
    await organismeComponentsPage.clickOnLastDeleteButton();

    organismeDeleteDialog = new OrganismeDeleteDialog();
    expect(await organismeDeleteDialog.getDialogTitle()).to.eq('tableAgricoleApp.organisme.delete.question');
    await organismeDeleteDialog.clickOnConfirmButton();

    expect(await organismeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
