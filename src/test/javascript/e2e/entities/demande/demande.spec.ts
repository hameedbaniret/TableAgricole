import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DemandeComponentsPage, DemandeDeleteDialog, DemandeUpdatePage } from './demande.page-object';

const expect = chai.expect;

describe('Demande e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let demandeComponentsPage: DemandeComponentsPage;
  let demandeUpdatePage: DemandeUpdatePage;
  let demandeDeleteDialog: DemandeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Demandes', async () => {
    await navBarPage.goToEntity('demande');
    demandeComponentsPage = new DemandeComponentsPage();
    await browser.wait(ec.visibilityOf(demandeComponentsPage.title), 5000);
    expect(await demandeComponentsPage.getTitle()).to.eq('tableAgricoleApp.demande.home.title');
    await browser.wait(ec.or(ec.visibilityOf(demandeComponentsPage.entities), ec.visibilityOf(demandeComponentsPage.noResult)), 1000);
  });

  it('should load create Demande page', async () => {
    await demandeComponentsPage.clickOnCreateButton();
    demandeUpdatePage = new DemandeUpdatePage();
    expect(await demandeUpdatePage.getPageTitle()).to.eq('tableAgricoleApp.demande.home.createOrEditLabel');
    await demandeUpdatePage.cancel();
  });

  it('should create and save Demandes', async () => {
    const nbButtonsBeforeCreate = await demandeComponentsPage.countDeleteButtons();

    await demandeComponentsPage.clickOnCreateButton();

    await promise.all([
      demandeUpdatePage.setIdDemandeInput('5'),
      demandeUpdatePage.frequenceSelectLastOption(),
      demandeUpdatePage.setBesoinTechniqueInput('besoinTechnique'),
      demandeUpdatePage.setBesoinUrgentInput('besoinUrgent'),
      demandeUpdatePage.setDateInspectionInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      demandeUpdatePage.setNombreBeneficiaireInput('5'),
      demandeUpdatePage.setPrcAugmentationNbBenefInput('5'),
      demandeUpdatePage.setPrcRHPerdueInput('5'),
      demandeUpdatePage.setPrcBenevolePerduInput('5'),
      demandeUpdatePage.setBoiteSuggestionInput('boiteSuggestion'),
      demandeUpdatePage.setAutreRessourceInput('autreRessource'),
      demandeUpdatePage.distanceVehSelectLastOption(),
      demandeUpdatePage.organismeSelectLastOption(),
      demandeUpdatePage.distanceVeloSelectLastOption(),
      demandeUpdatePage.etatFrigoSelectLastOption(),
    ]);

    expect(await demandeUpdatePage.getIdDemandeInput()).to.eq('5', 'Expected idDemande value to be equals to 5');
    const selectedFlagTypeDepDepannageUrgence = demandeUpdatePage.getFlagTypeDepDepannageUrgenceInput();
    if (await selectedFlagTypeDepDepannageUrgence.isSelected()) {
      await demandeUpdatePage.getFlagTypeDepDepannageUrgenceInput().click();
      expect(
        await demandeUpdatePage.getFlagTypeDepDepannageUrgenceInput().isSelected(),
        'Expected flagTypeDepDepannageUrgence not to be selected'
      ).to.be.false;
    } else {
      await demandeUpdatePage.getFlagTypeDepDepannageUrgenceInput().click();
      expect(
        await demandeUpdatePage.getFlagTypeDepDepannageUrgenceInput().isSelected(),
        'Expected flagTypeDepDepannageUrgence to be selected'
      ).to.be.true;
    }
    const selectedFlagTypeDepDepannageRegulier = demandeUpdatePage.getFlagTypeDepDepannageRegulierInput();
    if (await selectedFlagTypeDepDepannageRegulier.isSelected()) {
      await demandeUpdatePage.getFlagTypeDepDepannageRegulierInput().click();
      expect(
        await demandeUpdatePage.getFlagTypeDepDepannageRegulierInput().isSelected(),
        'Expected flagTypeDepDepannageRegulier not to be selected'
      ).to.be.false;
    } else {
      await demandeUpdatePage.getFlagTypeDepDepannageRegulierInput().click();
      expect(
        await demandeUpdatePage.getFlagTypeDepDepannageRegulierInput().isSelected(),
        'Expected flagTypeDepDepannageRegulier to be selected'
      ).to.be.true;
    }
    const selectedFlagTypeDepPopoteRoulante = demandeUpdatePage.getFlagTypeDepPopoteRoulanteInput();
    if (await selectedFlagTypeDepPopoteRoulante.isSelected()) {
      await demandeUpdatePage.getFlagTypeDepPopoteRoulanteInput().click();
      expect(
        await demandeUpdatePage.getFlagTypeDepPopoteRoulanteInput().isSelected(),
        'Expected flagTypeDepPopoteRoulante not to be selected'
      ).to.be.false;
    } else {
      await demandeUpdatePage.getFlagTypeDepPopoteRoulanteInput().click();
      expect(await demandeUpdatePage.getFlagTypeDepPopoteRoulanteInput().isSelected(), 'Expected flagTypeDepPopoteRoulante to be selected')
        .to.be.true;
    }
    const selectedFlagTypeDepMarcheAbordable = demandeUpdatePage.getFlagTypeDepMarcheAbordableInput();
    if (await selectedFlagTypeDepMarcheAbordable.isSelected()) {
      await demandeUpdatePage.getFlagTypeDepMarcheAbordableInput().click();
      expect(
        await demandeUpdatePage.getFlagTypeDepMarcheAbordableInput().isSelected(),
        'Expected flagTypeDepMarcheAbordable not to be selected'
      ).to.be.false;
    } else {
      await demandeUpdatePage.getFlagTypeDepMarcheAbordableInput().click();
      expect(
        await demandeUpdatePage.getFlagTypeDepMarcheAbordableInput().isSelected(),
        'Expected flagTypeDepMarcheAbordable to be selected'
      ).to.be.true;
    }
    const selectedFlagTypeDepEntraideAlimentaire = demandeUpdatePage.getFlagTypeDepEntraideAlimentaireInput();
    if (await selectedFlagTypeDepEntraideAlimentaire.isSelected()) {
      await demandeUpdatePage.getFlagTypeDepEntraideAlimentaireInput().click();
      expect(
        await demandeUpdatePage.getFlagTypeDepEntraideAlimentaireInput().isSelected(),
        'Expected flagTypeDepEntraideAlimentaire not to be selected'
      ).to.be.false;
    } else {
      await demandeUpdatePage.getFlagTypeDepEntraideAlimentaireInput().click();
      expect(
        await demandeUpdatePage.getFlagTypeDepEntraideAlimentaireInput().isSelected(),
        'Expected flagTypeDepEntraideAlimentaire to be selected'
      ).to.be.true;
    }
    const selectedFlagTypeDepFrigoPartage = demandeUpdatePage.getFlagTypeDepFrigoPartageInput();
    if (await selectedFlagTypeDepFrigoPartage.isSelected()) {
      await demandeUpdatePage.getFlagTypeDepFrigoPartageInput().click();
      expect(await demandeUpdatePage.getFlagTypeDepFrigoPartageInput().isSelected(), 'Expected flagTypeDepFrigoPartage not to be selected')
        .to.be.false;
    } else {
      await demandeUpdatePage.getFlagTypeDepFrigoPartageInput().click();
      expect(await demandeUpdatePage.getFlagTypeDepFrigoPartageInput().isSelected(), 'Expected flagTypeDepFrigoPartage to be selected').to
        .be.true;
    }
    const selectedFlagTypeDepAutre = demandeUpdatePage.getFlagTypeDepAutreInput();
    if (await selectedFlagTypeDepAutre.isSelected()) {
      await demandeUpdatePage.getFlagTypeDepAutreInput().click();
      expect(await demandeUpdatePage.getFlagTypeDepAutreInput().isSelected(), 'Expected flagTypeDepAutre not to be selected').to.be.false;
    } else {
      await demandeUpdatePage.getFlagTypeDepAutreInput().click();
      expect(await demandeUpdatePage.getFlagTypeDepAutreInput().isSelected(), 'Expected flagTypeDepAutre to be selected').to.be.true;
    }
    expect(await demandeUpdatePage.getBesoinTechniqueInput()).to.eq(
      'besoinTechnique',
      'Expected BesoinTechnique value to be equals to besoinTechnique'
    );
    expect(await demandeUpdatePage.getBesoinUrgentInput()).to.eq(
      'besoinUrgent',
      'Expected BesoinUrgent value to be equals to besoinUrgent'
    );
    const selectedFlagCamionRegrigere = demandeUpdatePage.getFlagCamionRegrigereInput();
    if (await selectedFlagCamionRegrigere.isSelected()) {
      await demandeUpdatePage.getFlagCamionRegrigereInput().click();
      expect(await demandeUpdatePage.getFlagCamionRegrigereInput().isSelected(), 'Expected flagCamionRegrigere not to be selected').to.be
        .false;
    } else {
      await demandeUpdatePage.getFlagCamionRegrigereInput().click();
      expect(await demandeUpdatePage.getFlagCamionRegrigereInput().isSelected(), 'Expected flagCamionRegrigere to be selected').to.be.true;
    }
    const selectedFlagPasCongelateur = demandeUpdatePage.getFlagPasCongelateurInput();
    if (await selectedFlagPasCongelateur.isSelected()) {
      await demandeUpdatePage.getFlagPasCongelateurInput().click();
      expect(await demandeUpdatePage.getFlagPasCongelateurInput().isSelected(), 'Expected flagPasCongelateur not to be selected').to.be
        .false;
    } else {
      await demandeUpdatePage.getFlagPasCongelateurInput().click();
      expect(await demandeUpdatePage.getFlagPasCongelateurInput().isSelected(), 'Expected flagPasCongelateur to be selected').to.be.true;
    }
    const selectedFlagCongelateurInd = demandeUpdatePage.getFlagCongelateurIndInput();
    if (await selectedFlagCongelateurInd.isSelected()) {
      await demandeUpdatePage.getFlagCongelateurIndInput().click();
      expect(await demandeUpdatePage.getFlagCongelateurIndInput().isSelected(), 'Expected flagCongelateurInd not to be selected').to.be
        .false;
    } else {
      await demandeUpdatePage.getFlagCongelateurIndInput().click();
      expect(await demandeUpdatePage.getFlagCongelateurIndInput().isSelected(), 'Expected flagCongelateurInd to be selected').to.be.true;
    }
    const selectedFlagCongelateurPlainPied = demandeUpdatePage.getFlagCongelateurPlainPiedInput();
    if (await selectedFlagCongelateurPlainPied.isSelected()) {
      await demandeUpdatePage.getFlagCongelateurPlainPiedInput().click();
      expect(
        await demandeUpdatePage.getFlagCongelateurPlainPiedInput().isSelected(),
        'Expected flagCongelateurPlainPied not to be selected'
      ).to.be.false;
    } else {
      await demandeUpdatePage.getFlagCongelateurPlainPiedInput().click();
      expect(await demandeUpdatePage.getFlagCongelateurPlainPiedInput().isSelected(), 'Expected flagCongelateurPlainPied to be selected').to
        .be.true;
    }
    const selectedFlagCongelateurResidentiel = demandeUpdatePage.getFlagCongelateurResidentielInput();
    if (await selectedFlagCongelateurResidentiel.isSelected()) {
      await demandeUpdatePage.getFlagCongelateurResidentielInput().click();
      expect(
        await demandeUpdatePage.getFlagCongelateurResidentielInput().isSelected(),
        'Expected flagCongelateurResidentiel not to be selected'
      ).to.be.false;
    } else {
      await demandeUpdatePage.getFlagCongelateurResidentielInput().click();
      expect(
        await demandeUpdatePage.getFlagCongelateurResidentielInput().isSelected(),
        'Expected flagCongelateurResidentiel to be selected'
      ).to.be.true;
    }
    const selectedFlagPasRefrigerateur = demandeUpdatePage.getFlagPasRefrigerateurInput();
    if (await selectedFlagPasRefrigerateur.isSelected()) {
      await demandeUpdatePage.getFlagPasRefrigerateurInput().click();
      expect(await demandeUpdatePage.getFlagPasRefrigerateurInput().isSelected(), 'Expected flagPasRefrigerateur not to be selected').to.be
        .false;
    } else {
      await demandeUpdatePage.getFlagPasRefrigerateurInput().click();
      expect(await demandeUpdatePage.getFlagPasRefrigerateurInput().isSelected(), 'Expected flagPasRefrigerateur to be selected').to.be
        .true;
    }
    const selectedFlagRefrigerateurInd = demandeUpdatePage.getFlagRefrigerateurIndInput();
    if (await selectedFlagRefrigerateurInd.isSelected()) {
      await demandeUpdatePage.getFlagRefrigerateurIndInput().click();
      expect(await demandeUpdatePage.getFlagRefrigerateurIndInput().isSelected(), 'Expected flagRefrigerateurInd not to be selected').to.be
        .false;
    } else {
      await demandeUpdatePage.getFlagRefrigerateurIndInput().click();
      expect(await demandeUpdatePage.getFlagRefrigerateurIndInput().isSelected(), 'Expected flagRefrigerateurInd to be selected').to.be
        .true;
    }
    const selectedFlagRefrigerateurPlainPied = demandeUpdatePage.getFlagRefrigerateurPlainPiedInput();
    if (await selectedFlagRefrigerateurPlainPied.isSelected()) {
      await demandeUpdatePage.getFlagRefrigerateurPlainPiedInput().click();
      expect(
        await demandeUpdatePage.getFlagRefrigerateurPlainPiedInput().isSelected(),
        'Expected flagRefrigerateurPlainPied not to be selected'
      ).to.be.false;
    } else {
      await demandeUpdatePage.getFlagRefrigerateurPlainPiedInput().click();
      expect(
        await demandeUpdatePage.getFlagRefrigerateurPlainPiedInput().isSelected(),
        'Expected flagRefrigerateurPlainPied to be selected'
      ).to.be.true;
    }
    const selectedFlagRefrigerateurResidentiel = demandeUpdatePage.getFlagRefrigerateurResidentielInput();
    if (await selectedFlagRefrigerateurResidentiel.isSelected()) {
      await demandeUpdatePage.getFlagRefrigerateurResidentielInput().click();
      expect(
        await demandeUpdatePage.getFlagRefrigerateurResidentielInput().isSelected(),
        'Expected flagRefrigerateurResidentiel not to be selected'
      ).to.be.false;
    } else {
      await demandeUpdatePage.getFlagRefrigerateurResidentielInput().click();
      expect(
        await demandeUpdatePage.getFlagRefrigerateurResidentielInput().isSelected(),
        'Expected flagRefrigerateurResidentiel to be selected'
      ).to.be.true;
    }
    const selectedFlagAccesGlaciere = demandeUpdatePage.getFlagAccesGlaciereInput();
    if (await selectedFlagAccesGlaciere.isSelected()) {
      await demandeUpdatePage.getFlagAccesGlaciereInput().click();
      expect(await demandeUpdatePage.getFlagAccesGlaciereInput().isSelected(), 'Expected flagAccesGlaciere not to be selected').to.be.false;
    } else {
      await demandeUpdatePage.getFlagAccesGlaciereInput().click();
      expect(await demandeUpdatePage.getFlagAccesGlaciereInput().isSelected(), 'Expected flagAccesGlaciere to be selected').to.be.true;
    }
    const selectedFlagAccesCuisine = demandeUpdatePage.getFlagAccesCuisineInput();
    if (await selectedFlagAccesCuisine.isSelected()) {
      await demandeUpdatePage.getFlagAccesCuisineInput().click();
      expect(await demandeUpdatePage.getFlagAccesCuisineInput().isSelected(), 'Expected flagAccesCuisine not to be selected').to.be.false;
    } else {
      await demandeUpdatePage.getFlagAccesCuisineInput().click();
      expect(await demandeUpdatePage.getFlagAccesCuisineInput().isSelected(), 'Expected flagAccesCuisine to be selected').to.be.true;
    }
    const selectedFlagAccesVehicule = demandeUpdatePage.getFlagAccesVehiculeInput();
    if (await selectedFlagAccesVehicule.isSelected()) {
      await demandeUpdatePage.getFlagAccesVehiculeInput().click();
      expect(await demandeUpdatePage.getFlagAccesVehiculeInput().isSelected(), 'Expected flagAccesVehicule not to be selected').to.be.false;
    } else {
      await demandeUpdatePage.getFlagAccesVehiculeInput().click();
      expect(await demandeUpdatePage.getFlagAccesVehiculeInput().isSelected(), 'Expected flagAccesVehicule to be selected').to.be.true;
    }
    expect(await demandeUpdatePage.getDateInspectionInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateInspection value to be equals to 2000-12-31'
    );
    expect(await demandeUpdatePage.getNombreBeneficiaireInput()).to.eq('5', 'Expected nombreBeneficiaire value to be equals to 5');
    expect(await demandeUpdatePage.getPrcAugmentationNbBenefInput()).to.eq('5', 'Expected prcAugmentationNbBenef value to be equals to 5');
    expect(await demandeUpdatePage.getPrcRHPerdueInput()).to.eq('5', 'Expected prcRHPerdue value to be equals to 5');
    expect(await demandeUpdatePage.getPrcBenevolePerduInput()).to.eq('5', 'Expected prcBenevolePerdu value to be equals to 5');
    const selectedFlagDenreeSuffisante = demandeUpdatePage.getFlagDenreeSuffisanteInput();
    if (await selectedFlagDenreeSuffisante.isSelected()) {
      await demandeUpdatePage.getFlagDenreeSuffisanteInput().click();
      expect(await demandeUpdatePage.getFlagDenreeSuffisanteInput().isSelected(), 'Expected flagDenreeSuffisante not to be selected').to.be
        .false;
    } else {
      await demandeUpdatePage.getFlagDenreeSuffisanteInput().click();
      expect(await demandeUpdatePage.getFlagDenreeSuffisanteInput().isSelected(), 'Expected flagDenreeSuffisante to be selected').to.be
        .true;
    }
    const selectedFlagRHSuffisant = demandeUpdatePage.getFlagRHSuffisantInput();
    if (await selectedFlagRHSuffisant.isSelected()) {
      await demandeUpdatePage.getFlagRHSuffisantInput().click();
      expect(await demandeUpdatePage.getFlagRHSuffisantInput().isSelected(), 'Expected flagRHSuffisant not to be selected').to.be.false;
    } else {
      await demandeUpdatePage.getFlagRHSuffisantInput().click();
      expect(await demandeUpdatePage.getFlagRHSuffisantInput().isSelected(), 'Expected flagRHSuffisant to be selected').to.be.true;
    }
    const selectedFlagVegetarien = demandeUpdatePage.getFlagVegetarienInput();
    if (await selectedFlagVegetarien.isSelected()) {
      await demandeUpdatePage.getFlagVegetarienInput().click();
      expect(await demandeUpdatePage.getFlagVegetarienInput().isSelected(), 'Expected flagVegetarien not to be selected').to.be.false;
    } else {
      await demandeUpdatePage.getFlagVegetarienInput().click();
      expect(await demandeUpdatePage.getFlagVegetarienInput().isSelected(), 'Expected flagVegetarien to be selected').to.be.true;
    }
    const selectedFlagVegetalien = demandeUpdatePage.getFlagVegetalienInput();
    if (await selectedFlagVegetalien.isSelected()) {
      await demandeUpdatePage.getFlagVegetalienInput().click();
      expect(await demandeUpdatePage.getFlagVegetalienInput().isSelected(), 'Expected flagVegetalien not to be selected').to.be.false;
    } else {
      await demandeUpdatePage.getFlagVegetalienInput().click();
      expect(await demandeUpdatePage.getFlagVegetalienInput().isSelected(), 'Expected flagVegetalien to be selected').to.be.true;
    }
    const selectedFlagItemSansEtiquette = demandeUpdatePage.getFlagItemSansEtiquetteInput();
    if (await selectedFlagItemSansEtiquette.isSelected()) {
      await demandeUpdatePage.getFlagItemSansEtiquetteInput().click();
      expect(await demandeUpdatePage.getFlagItemSansEtiquetteInput().isSelected(), 'Expected flagItemSansEtiquette not to be selected').to
        .be.false;
    } else {
      await demandeUpdatePage.getFlagItemSansEtiquetteInput().click();
      expect(await demandeUpdatePage.getFlagItemSansEtiquetteInput().isSelected(), 'Expected flagItemSansEtiquette to be selected').to.be
        .true;
    }
    const selectedFlagItemDate = demandeUpdatePage.getFlagItemDateInput();
    if (await selectedFlagItemDate.isSelected()) {
      await demandeUpdatePage.getFlagItemDateInput().click();
      expect(await demandeUpdatePage.getFlagItemDateInput().isSelected(), 'Expected flagItemDate not to be selected').to.be.false;
    } else {
      await demandeUpdatePage.getFlagItemDateInput().click();
      expect(await demandeUpdatePage.getFlagItemDateInput().isSelected(), 'Expected flagItemDate to be selected').to.be.true;
    }
    const selectedFlagHalal = demandeUpdatePage.getFlagHalalInput();
    if (await selectedFlagHalal.isSelected()) {
      await demandeUpdatePage.getFlagHalalInput().click();
      expect(await demandeUpdatePage.getFlagHalalInput().isSelected(), 'Expected flagHalal not to be selected').to.be.false;
    } else {
      await demandeUpdatePage.getFlagHalalInput().click();
      expect(await demandeUpdatePage.getFlagHalalInput().isSelected(), 'Expected flagHalal to be selected').to.be.true;
    }
    const selectedFlagKasher = demandeUpdatePage.getFlagKasherInput();
    if (await selectedFlagKasher.isSelected()) {
      await demandeUpdatePage.getFlagKasherInput().click();
      expect(await demandeUpdatePage.getFlagKasherInput().isSelected(), 'Expected flagKasher not to be selected').to.be.false;
    } else {
      await demandeUpdatePage.getFlagKasherInput().click();
      expect(await demandeUpdatePage.getFlagKasherInput().isSelected(), 'Expected flagKasher to be selected').to.be.true;
    }
    expect(await demandeUpdatePage.getBoiteSuggestionInput()).to.eq(
      'boiteSuggestion',
      'Expected BoiteSuggestion value to be equals to boiteSuggestion'
    );
    expect(await demandeUpdatePage.getAutreRessourceInput()).to.eq(
      'autreRessource',
      'Expected AutreRessource value to be equals to autreRessource'
    );

    await demandeUpdatePage.save();
    expect(await demandeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await demandeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Demande', async () => {
    const nbButtonsBeforeDelete = await demandeComponentsPage.countDeleteButtons();
    await demandeComponentsPage.clickOnLastDeleteButton();

    demandeDeleteDialog = new DemandeDeleteDialog();
    expect(await demandeDeleteDialog.getDialogTitle()).to.eq('tableAgricoleApp.demande.delete.question');
    await demandeDeleteDialog.clickOnConfirmButton();

    expect(await demandeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
