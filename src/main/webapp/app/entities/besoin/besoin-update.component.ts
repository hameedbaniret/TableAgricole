import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBesoin, Besoin } from 'app/shared/model/besoin.model';
import { BesoinService } from './besoin.service';
import { ITypeAliment } from 'app/shared/model/type-aliment.model';
import { TypeAlimentService } from 'app/entities/type-aliment/type-aliment.service';
import { IAliment } from 'app/shared/model/aliment.model';
import { AlimentService } from 'app/entities/aliment/aliment.service';
import { IDemande } from 'app/shared/model/demande.model';
import { DemandeService } from 'app/entities/demande/demande.service';

type SelectableEntity = ITypeAliment | IAliment | IDemande;

@Component({
  selector: 'jhi-besoin-update',
  templateUrl: './besoin-update.component.html',
})
export class BesoinUpdateComponent implements OnInit {
  isSaving = false;
  typealiments: ITypeAliment[] = [];
  aliments: IAliment[] = [];
  demandes: IDemande[] = [];

  editForm = this.fb.group({
    id: [],
    idBesoin: [],
    besoinFrequence: [],
    typeBesoin: [],
    quantite: [],
    datePeremption: [],
    dateBesoin: [],
    titreEmploi: [],
    tachePrincipale: [],
    nbHeure: [],
    dureeContrat: [],
    dateEntree: [],
    nbBeneficiaire: [],
    serviceSouhaite: [],
    dateRecuperation: [],
    typeAlimentId: [],
    alimentId: [],
    demandeId: [],
  });

  constructor(
    protected besoinService: BesoinService,
    protected typeAlimentService: TypeAlimentService,
    protected alimentService: AlimentService,
    protected demandeService: DemandeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ besoin }) => {
      if (!besoin.id) {
        const today = moment().startOf('day');
        besoin.datePeremption = today;
        besoin.dateBesoin = today;
        besoin.dateEntree = today;
        besoin.dateRecuperation = today;
      }

      this.updateForm(besoin);

      this.typeAlimentService.query().subscribe((res: HttpResponse<ITypeAliment[]>) => (this.typealiments = res.body || []));

      this.alimentService.query().subscribe((res: HttpResponse<IAliment[]>) => (this.aliments = res.body || []));

      this.demandeService.query().subscribe((res: HttpResponse<IDemande[]>) => (this.demandes = res.body || []));
    });
  }

  updateForm(besoin: IBesoin): void {
    this.editForm.patchValue({
      id: besoin.id,
      idBesoin: besoin.idBesoin,
      besoinFrequence: besoin.besoinFrequence,
      typeBesoin: besoin.typeBesoin,
      quantite: besoin.quantite,
      datePeremption: besoin.datePeremption ? besoin.datePeremption.format(DATE_TIME_FORMAT) : null,
      dateBesoin: besoin.dateBesoin ? besoin.dateBesoin.format(DATE_TIME_FORMAT) : null,
      titreEmploi: besoin.titreEmploi,
      tachePrincipale: besoin.tachePrincipale,
      nbHeure: besoin.nbHeure,
      dureeContrat: besoin.dureeContrat,
      dateEntree: besoin.dateEntree ? besoin.dateEntree.format(DATE_TIME_FORMAT) : null,
      nbBeneficiaire: besoin.nbBeneficiaire,
      serviceSouhaite: besoin.serviceSouhaite,
      dateRecuperation: besoin.dateRecuperation ? besoin.dateRecuperation.format(DATE_TIME_FORMAT) : null,
      typeAlimentId: besoin.typeAlimentId,
      alimentId: besoin.alimentId,
      demandeId: besoin.demandeId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const besoin = this.createFromForm();
    if (besoin.id !== undefined) {
      this.subscribeToSaveResponse(this.besoinService.update(besoin));
    } else {
      this.subscribeToSaveResponse(this.besoinService.create(besoin));
    }
  }

  private createFromForm(): IBesoin {
    return {
      ...new Besoin(),
      id: this.editForm.get(['id'])!.value,
      idBesoin: this.editForm.get(['idBesoin'])!.value,
      besoinFrequence: this.editForm.get(['besoinFrequence'])!.value,
      typeBesoin: this.editForm.get(['typeBesoin'])!.value,
      quantite: this.editForm.get(['quantite'])!.value,
      datePeremption: this.editForm.get(['datePeremption'])!.value
        ? moment(this.editForm.get(['datePeremption'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dateBesoin: this.editForm.get(['dateBesoin'])!.value ? moment(this.editForm.get(['dateBesoin'])!.value, DATE_TIME_FORMAT) : undefined,
      titreEmploi: this.editForm.get(['titreEmploi'])!.value,
      tachePrincipale: this.editForm.get(['tachePrincipale'])!.value,
      nbHeure: this.editForm.get(['nbHeure'])!.value,
      dureeContrat: this.editForm.get(['dureeContrat'])!.value,
      dateEntree: this.editForm.get(['dateEntree'])!.value ? moment(this.editForm.get(['dateEntree'])!.value, DATE_TIME_FORMAT) : undefined,
      nbBeneficiaire: this.editForm.get(['nbBeneficiaire'])!.value,
      serviceSouhaite: this.editForm.get(['serviceSouhaite'])!.value,
      dateRecuperation: this.editForm.get(['dateRecuperation'])!.value
        ? moment(this.editForm.get(['dateRecuperation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      typeAlimentId: this.editForm.get(['typeAlimentId'])!.value,
      alimentId: this.editForm.get(['alimentId'])!.value,
      demandeId: this.editForm.get(['demandeId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBesoin>>): void {
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
