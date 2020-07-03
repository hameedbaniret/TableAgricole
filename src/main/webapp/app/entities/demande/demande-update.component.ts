import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IDemande, Demande } from 'app/shared/model/demande.model';
import { DemandeService } from './demande.service';
import { IDistanceVeh } from 'app/shared/model/distance-veh.model';
import { DistanceVehService } from 'app/entities/distance-veh/distance-veh.service';
import { IOrganisme } from 'app/shared/model/organisme.model';
import { OrganismeService } from 'app/entities/organisme/organisme.service';
import { IDistanceVelo } from 'app/shared/model/distance-velo.model';
import { DistanceVeloService } from 'app/entities/distance-velo/distance-velo.service';
import { IEtatFrigo } from 'app/shared/model/etat-frigo.model';
import { EtatFrigoService } from 'app/entities/etat-frigo/etat-frigo.service';

type SelectableEntity = IDistanceVeh | IOrganisme | IDistanceVelo | IEtatFrigo;

@Component({
  selector: 'jhi-demande-update',
  templateUrl: './demande-update.component.html',
})
export class DemandeUpdateComponent implements OnInit {
  isSaving = false;
  distancevehs: IDistanceVeh[] = [];
  organismes: IOrganisme[] = [];
  distancevelos: IDistanceVelo[] = [];
  etatfrigos: IEtatFrigo[] = [];

  editForm = this.fb.group({
    id: [],
    idDemande: [],
    frequence: [],
    flagTypeDepDepannageUrgence: [],
    flagTypeDepDepannageRegulier: [],
    flagTypeDepPopoteRoulante: [],
    flagTypeDepMarcheAbordable: [],
    flagTypeDepEntraideAlimentaire: [],
    flagTypeDepFrigoPartage: [],
    flagTypeDepAutre: [],
    besoinTechnique: [],
    besoinUrgent: [],
    flagCamionRegrigere: [],
    flagPasCongelateur: [],
    flagCongelateurInd: [],
    flagCongelateurPlainPied: [],
    flagCongelateurResidentiel: [],
    flagPasRefrigerateur: [],
    flagRefrigerateurInd: [],
    flagRefrigerateurPlainPied: [],
    flagRefrigerateurResidentiel: [],
    flagAccesGlaciere: [],
    flagAccesCuisine: [],
    flagAccesVehicule: [],
    dateInspection: [],
    nombreBeneficiaire: [],
    prcAugmentationNbBenef: [],
    prcRHPerdue: [],
    prcBenevolePerdu: [],
    flagDenreeSuffisante: [],
    flagRHSuffisant: [],
    flagVegetarien: [],
    flagVegetalien: [],
    flagItemSansEtiquette: [],
    flagItemDate: [],
    flagHalal: [],
    flagKasher: [],
    boiteSuggestion: [],
    autreRessource: [],
    distanceVehId: [],
    organismeId: [],
    distanceVeloId: [],
    etatFrigoId: [],
  });

  constructor(
    protected demandeService: DemandeService,
    protected distanceVehService: DistanceVehService,
    protected organismeService: OrganismeService,
    protected distanceVeloService: DistanceVeloService,
    protected etatFrigoService: EtatFrigoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ demande }) => {
      if (!demande.id) {
        const today = moment().startOf('day');
        demande.dateInspection = today;
      }

      this.updateForm(demande);

      this.distanceVehService.query().subscribe((res: HttpResponse<IDistanceVeh[]>) => (this.distancevehs = res.body || []));

      this.organismeService.query().subscribe((res: HttpResponse<IOrganisme[]>) => (this.organismes = res.body || []));

      this.distanceVeloService.query().subscribe((res: HttpResponse<IDistanceVelo[]>) => (this.distancevelos = res.body || []));

      this.etatFrigoService.query().subscribe((res: HttpResponse<IEtatFrigo[]>) => (this.etatfrigos = res.body || []));
    });
  }

  updateForm(demande: IDemande): void {
    this.editForm.patchValue({
      id: demande.id,
      idDemande: demande.idDemande,
      frequence: demande.frequence,
      flagTypeDepDepannageUrgence: demande.flagTypeDepDepannageUrgence,
      flagTypeDepDepannageRegulier: demande.flagTypeDepDepannageRegulier,
      flagTypeDepPopoteRoulante: demande.flagTypeDepPopoteRoulante,
      flagTypeDepMarcheAbordable: demande.flagTypeDepMarcheAbordable,
      flagTypeDepEntraideAlimentaire: demande.flagTypeDepEntraideAlimentaire,
      flagTypeDepFrigoPartage: demande.flagTypeDepFrigoPartage,
      flagTypeDepAutre: demande.flagTypeDepAutre,
      besoinTechnique: demande.besoinTechnique,
      besoinUrgent: demande.besoinUrgent,
      flagCamionRegrigere: demande.flagCamionRegrigere,
      flagPasCongelateur: demande.flagPasCongelateur,
      flagCongelateurInd: demande.flagCongelateurInd,
      flagCongelateurPlainPied: demande.flagCongelateurPlainPied,
      flagCongelateurResidentiel: demande.flagCongelateurResidentiel,
      flagPasRefrigerateur: demande.flagPasRefrigerateur,
      flagRefrigerateurInd: demande.flagRefrigerateurInd,
      flagRefrigerateurPlainPied: demande.flagRefrigerateurPlainPied,
      flagRefrigerateurResidentiel: demande.flagRefrigerateurResidentiel,
      flagAccesGlaciere: demande.flagAccesGlaciere,
      flagAccesCuisine: demande.flagAccesCuisine,
      flagAccesVehicule: demande.flagAccesVehicule,
      dateInspection: demande.dateInspection ? demande.dateInspection.format(DATE_TIME_FORMAT) : null,
      nombreBeneficiaire: demande.nombreBeneficiaire,
      prcAugmentationNbBenef: demande.prcAugmentationNbBenef,
      prcRHPerdue: demande.prcRHPerdue,
      prcBenevolePerdu: demande.prcBenevolePerdu,
      flagDenreeSuffisante: demande.flagDenreeSuffisante,
      flagRHSuffisant: demande.flagRHSuffisant,
      flagVegetarien: demande.flagVegetarien,
      flagVegetalien: demande.flagVegetalien,
      flagItemSansEtiquette: demande.flagItemSansEtiquette,
      flagItemDate: demande.flagItemDate,
      flagHalal: demande.flagHalal,
      flagKasher: demande.flagKasher,
      boiteSuggestion: demande.boiteSuggestion,
      autreRessource: demande.autreRessource,
      distanceVehId: demande.distanceVehId,
      organismeId: demande.organismeId,
      distanceVeloId: demande.distanceVeloId,
      etatFrigoId: demande.etatFrigoId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const demande = this.createFromForm();
    if (demande.id !== undefined) {
      this.subscribeToSaveResponse(this.demandeService.update(demande));
    } else {
      this.subscribeToSaveResponse(this.demandeService.create(demande));
    }
  }

  private createFromForm(): IDemande {
    return {
      ...new Demande(),
      id: this.editForm.get(['id'])!.value,
      idDemande: this.editForm.get(['idDemande'])!.value,
      frequence: this.editForm.get(['frequence'])!.value,
      flagTypeDepDepannageUrgence: this.editForm.get(['flagTypeDepDepannageUrgence'])!.value,
      flagTypeDepDepannageRegulier: this.editForm.get(['flagTypeDepDepannageRegulier'])!.value,
      flagTypeDepPopoteRoulante: this.editForm.get(['flagTypeDepPopoteRoulante'])!.value,
      flagTypeDepMarcheAbordable: this.editForm.get(['flagTypeDepMarcheAbordable'])!.value,
      flagTypeDepEntraideAlimentaire: this.editForm.get(['flagTypeDepEntraideAlimentaire'])!.value,
      flagTypeDepFrigoPartage: this.editForm.get(['flagTypeDepFrigoPartage'])!.value,
      flagTypeDepAutre: this.editForm.get(['flagTypeDepAutre'])!.value,
      besoinTechnique: this.editForm.get(['besoinTechnique'])!.value,
      besoinUrgent: this.editForm.get(['besoinUrgent'])!.value,
      flagCamionRegrigere: this.editForm.get(['flagCamionRegrigere'])!.value,
      flagPasCongelateur: this.editForm.get(['flagPasCongelateur'])!.value,
      flagCongelateurInd: this.editForm.get(['flagCongelateurInd'])!.value,
      flagCongelateurPlainPied: this.editForm.get(['flagCongelateurPlainPied'])!.value,
      flagCongelateurResidentiel: this.editForm.get(['flagCongelateurResidentiel'])!.value,
      flagPasRefrigerateur: this.editForm.get(['flagPasRefrigerateur'])!.value,
      flagRefrigerateurInd: this.editForm.get(['flagRefrigerateurInd'])!.value,
      flagRefrigerateurPlainPied: this.editForm.get(['flagRefrigerateurPlainPied'])!.value,
      flagRefrigerateurResidentiel: this.editForm.get(['flagRefrigerateurResidentiel'])!.value,
      flagAccesGlaciere: this.editForm.get(['flagAccesGlaciere'])!.value,
      flagAccesCuisine: this.editForm.get(['flagAccesCuisine'])!.value,
      flagAccesVehicule: this.editForm.get(['flagAccesVehicule'])!.value,
      dateInspection: this.editForm.get(['dateInspection'])!.value
        ? moment(this.editForm.get(['dateInspection'])!.value, DATE_TIME_FORMAT)
        : undefined,
      nombreBeneficiaire: this.editForm.get(['nombreBeneficiaire'])!.value,
      prcAugmentationNbBenef: this.editForm.get(['prcAugmentationNbBenef'])!.value,
      prcRHPerdue: this.editForm.get(['prcRHPerdue'])!.value,
      prcBenevolePerdu: this.editForm.get(['prcBenevolePerdu'])!.value,
      flagDenreeSuffisante: this.editForm.get(['flagDenreeSuffisante'])!.value,
      flagRHSuffisant: this.editForm.get(['flagRHSuffisant'])!.value,
      flagVegetarien: this.editForm.get(['flagVegetarien'])!.value,
      flagVegetalien: this.editForm.get(['flagVegetalien'])!.value,
      flagItemSansEtiquette: this.editForm.get(['flagItemSansEtiquette'])!.value,
      flagItemDate: this.editForm.get(['flagItemDate'])!.value,
      flagHalal: this.editForm.get(['flagHalal'])!.value,
      flagKasher: this.editForm.get(['flagKasher'])!.value,
      boiteSuggestion: this.editForm.get(['boiteSuggestion'])!.value,
      autreRessource: this.editForm.get(['autreRessource'])!.value,
      distanceVehId: this.editForm.get(['distanceVehId'])!.value,
      organismeId: this.editForm.get(['organismeId'])!.value,
      distanceVeloId: this.editForm.get(['distanceVeloId'])!.value,
      etatFrigoId: this.editForm.get(['etatFrigoId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDemande>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
