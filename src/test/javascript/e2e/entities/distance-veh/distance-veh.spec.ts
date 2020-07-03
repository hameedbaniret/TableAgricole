import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DistanceVehComponentsPage, DistanceVehDeleteDialog, DistanceVehUpdatePage } from './distance-veh.page-object';

const expect = chai.expect;

describe('DistanceVeh e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let distanceVehComponentsPage: DistanceVehComponentsPage;
  let distanceVehUpdatePage: DistanceVehUpdatePage;
  let distanceVehDeleteDialog: DistanceVehDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DistanceVehs', async () => {
    await navBarPage.goToEntity('distance-veh');
    distanceVehComponentsPage = new DistanceVehComponentsPage();
    await browser.wait(ec.visibilityOf(distanceVehComponentsPage.title), 5000);
    expect(await distanceVehComponentsPage.getTitle()).to.eq('tableAgricoleApp.distanceVeh.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(distanceVehComponentsPage.entities), ec.visibilityOf(distanceVehComponentsPage.noResult)),
      1000
    );
  });

  it('should load create DistanceVeh page', async () => {
    await distanceVehComponentsPage.clickOnCreateButton();
    distanceVehUpdatePage = new DistanceVehUpdatePage();
    expect(await distanceVehUpdatePage.getPageTitle()).to.eq('tableAgricoleApp.distanceVeh.home.createOrEditLabel');
    await distanceVehUpdatePage.cancel();
  });

  it('should create and save DistanceVehs', async () => {
    const nbButtonsBeforeCreate = await distanceVehComponentsPage.countDeleteButtons();

    await distanceVehComponentsPage.clickOnCreateButton();

    await promise.all([distanceVehUpdatePage.setIdDistanceVehInput('5'), distanceVehUpdatePage.setDscDistanceVehInput('dscDistanceVeh')]);

    expect(await distanceVehUpdatePage.getIdDistanceVehInput()).to.eq('5', 'Expected idDistanceVeh value to be equals to 5');
    expect(await distanceVehUpdatePage.getDscDistanceVehInput()).to.eq(
      'dscDistanceVeh',
      'Expected DscDistanceVeh value to be equals to dscDistanceVeh'
    );

    await distanceVehUpdatePage.save();
    expect(await distanceVehUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await distanceVehComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last DistanceVeh', async () => {
    const nbButtonsBeforeDelete = await distanceVehComponentsPage.countDeleteButtons();
    await distanceVehComponentsPage.clickOnLastDeleteButton();

    distanceVehDeleteDialog = new DistanceVehDeleteDialog();
    expect(await distanceVehDeleteDialog.getDialogTitle()).to.eq('tableAgricoleApp.distanceVeh.delete.question');
    await distanceVehDeleteDialog.clickOnConfirmButton();

    expect(await distanceVehComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
