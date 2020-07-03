import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { BesoinComponentsPage, BesoinDeleteDialog, BesoinUpdatePage } from './besoin.page-object';

const expect = chai.expect;

describe('Besoin e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let besoinComponentsPage: BesoinComponentsPage;
  let besoinUpdatePage: BesoinUpdatePage;
  let besoinDeleteDialog: BesoinDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Besoins', async () => {
    await navBarPage.goToEntity('besoin');
    besoinComponentsPage = new BesoinComponentsPage();
    await browser.wait(ec.visibilityOf(besoinComponentsPage.title), 5000);
    expect(await besoinComponentsPage.getTitle()).to.eq('tableAgricoleApp.besoin.home.title');
    await browser.wait(ec.or(ec.visibilityOf(besoinComponentsPage.entities), ec.visibilityOf(besoinComponentsPage.noResult)), 1000);
  });

  it('should load create Besoin page', async () => {
    await besoinComponentsPage.clickOnCreateButton();
    besoinUpdatePage = new BesoinUpdatePage();
    expect(await besoinUpdatePage.getPageTitle()).to.eq('tableAgricoleApp.besoin.home.createOrEditLabel');
    await besoinUpdatePage.cancel();
  });

  it('should create and save Besoins', async () => {
    const nbButtonsBeforeCreate = await besoinComponentsPage.countDeleteButtons();

    await besoinComponentsPage.clickOnCreateButton();

    await promise.all([
      besoinUpdatePage.setIdBesoinInput('5'),
      besoinUpdatePage.besoinFrequenceSelectLastOption(),
      besoinUpdatePage.typeBesoinSelectLastOption(),
      besoinUpdatePage.setQuantiteInput('5'),
      besoinUpdatePage.setDatePeremptionInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      besoinUpdatePage.setDateBesoinInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      besoinUpdatePage.setTitreEmploiInput('titreEmploi'),
      besoinUpdatePage.setTachePrincipaleInput('tachePrincipale'),
      besoinUpdatePage.setNbHeureInput('5'),
      besoinUpdatePage.setDureeContratInput('5'),
      besoinUpdatePage.setDateEntreeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      besoinUpdatePage.setNbBeneficiaireInput('5'),
      besoinUpdatePage.setServiceSouhaiteInput('serviceSouhaite'),
      besoinUpdatePage.setDateRecuperationInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      besoinUpdatePage.typeAlimentSelectLastOption(),
      besoinUpdatePage.alimentSelectLastOption(),
      besoinUpdatePage.demandeSelectLastOption(),
    ]);

    expect(await besoinUpdatePage.getIdBesoinInput()).to.eq('5', 'Expected idBesoin value to be equals to 5');
    expect(await besoinUpdatePage.getQuantiteInput()).to.eq('5', 'Expected quantite value to be equals to 5');
    expect(await besoinUpdatePage.getDatePeremptionInput()).to.contain(
      '2001-01-01T02:30',
      'Expected datePeremption value to be equals to 2000-12-31'
    );
    expect(await besoinUpdatePage.getDateBesoinInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateBesoin value to be equals to 2000-12-31'
    );
    expect(await besoinUpdatePage.getTitreEmploiInput()).to.eq('titreEmploi', 'Expected TitreEmploi value to be equals to titreEmploi');
    expect(await besoinUpdatePage.getTachePrincipaleInput()).to.eq(
      'tachePrincipale',
      'Expected TachePrincipale value to be equals to tachePrincipale'
    );
    expect(await besoinUpdatePage.getNbHeureInput()).to.eq('5', 'Expected nbHeure value to be equals to 5');
    expect(await besoinUpdatePage.getDureeContratInput()).to.eq('5', 'Expected dureeContrat value to be equals to 5');
    expect(await besoinUpdatePage.getDateEntreeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateEntree value to be equals to 2000-12-31'
    );
    expect(await besoinUpdatePage.getNbBeneficiaireInput()).to.eq('5', 'Expected nbBeneficiaire value to be equals to 5');
    expect(await besoinUpdatePage.getServiceSouhaiteInput()).to.eq(
      'serviceSouhaite',
      'Expected ServiceSouhaite value to be equals to serviceSouhaite'
    );
    expect(await besoinUpdatePage.getDateRecuperationInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateRecuperation value to be equals to 2000-12-31'
    );

    await besoinUpdatePage.save();
    expect(await besoinUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await besoinComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Besoin', async () => {
    const nbButtonsBeforeDelete = await besoinComponentsPage.countDeleteButtons();
    await besoinComponentsPage.clickOnLastDeleteButton();

    besoinDeleteDialog = new BesoinDeleteDialog();
    expect(await besoinDeleteDialog.getDialogTitle()).to.eq('tableAgricoleApp.besoin.delete.question');
    await besoinDeleteDialog.clickOnConfirmButton();

    expect(await besoinComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
