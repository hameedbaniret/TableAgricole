import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DistanceVeloComponentsPage, DistanceVeloDeleteDialog, DistanceVeloUpdatePage } from './distance-velo.page-object';

const expect = chai.expect;

describe('DistanceVelo e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let distanceVeloComponentsPage: DistanceVeloComponentsPage;
  let distanceVeloUpdatePage: DistanceVeloUpdatePage;
  let distanceVeloDeleteDialog: DistanceVeloDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DistanceVelos', async () => {
    await navBarPage.goToEntity('distance-velo');
    distanceVeloComponentsPage = new DistanceVeloComponentsPage();
    await browser.wait(ec.visibilityOf(distanceVeloComponentsPage.title), 5000);
    expect(await distanceVeloComponentsPage.getTitle()).to.eq('tableAgricoleApp.distanceVelo.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(distanceVeloComponentsPage.entities), ec.visibilityOf(distanceVeloComponentsPage.noResult)),
      1000
    );
  });

  it('should load create DistanceVelo page', async () => {
    await distanceVeloComponentsPage.clickOnCreateButton();
    distanceVeloUpdatePage = new DistanceVeloUpdatePage();
    expect(await distanceVeloUpdatePage.getPageTitle()).to.eq('tableAgricoleApp.distanceVelo.home.createOrEditLabel');
    await distanceVeloUpdatePage.cancel();
  });

  it('should create and save DistanceVelos', async () => {
    const nbButtonsBeforeCreate = await distanceVeloComponentsPage.countDeleteButtons();

    await distanceVeloComponentsPage.clickOnCreateButton();

    await promise.all([
      distanceVeloUpdatePage.setIdDistanceVeloInput('5'),
      distanceVeloUpdatePage.setDscDistanceVeloInput('dscDistanceVelo'),
    ]);

    expect(await distanceVeloUpdatePage.getIdDistanceVeloInput()).to.eq('5', 'Expected idDistanceVelo value to be equals to 5');
    expect(await distanceVeloUpdatePage.getDscDistanceVeloInput()).to.eq(
      'dscDistanceVelo',
      'Expected DscDistanceVelo value to be equals to dscDistanceVelo'
    );

    await distanceVeloUpdatePage.save();
    expect(await distanceVeloUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await distanceVeloComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last DistanceVelo', async () => {
    const nbButtonsBeforeDelete = await distanceVeloComponentsPage.countDeleteButtons();
    await distanceVeloComponentsPage.clickOnLastDeleteButton();

    distanceVeloDeleteDialog = new DistanceVeloDeleteDialog();
    expect(await distanceVeloDeleteDialog.getDialogTitle()).to.eq('tableAgricoleApp.distanceVelo.delete.question');
    await distanceVeloDeleteDialog.clickOnConfirmButton();

    expect(await distanceVeloComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
