import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEtatFrigo, EtatFrigo } from 'app/shared/model/etat-frigo.model';
import { EtatFrigoService } from './etat-frigo.service';

@Component({
  selector: 'jhi-etat-frigo-update',
  templateUrl: './etat-frigo-update.component.html',
})
export class EtatFrigoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idEtatFrigo: [],
    dscEtatFrigo: [null, [Validators.required]],
  });

  constructor(protected etatFrigoService: EtatFrigoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ etatFrigo }) => {
      this.updateForm(etatFrigo);
    });
  }

  updateForm(etatFrigo: IEtatFrigo): void {
    this.editForm.patchValue({
      id: etatFrigo.id,
      idEtatFrigo: etatFrigo.idEtatFrigo,
      dscEtatFrigo: etatFrigo.dscEtatFrigo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const etatFrigo = this.createFromForm();
    if (etatFrigo.id !== undefined) {
      this.subscribeToSaveResponse(this.etatFrigoService.update(etatFrigo));
    } else {
      this.subscribeToSaveResponse(this.etatFrigoService.create(etatFrigo));
    }
  }

  private createFromForm(): IEtatFrigo {
    return {
      ...new EtatFrigo(),
      id: this.editForm.get(['id'])!.value,
      idEtatFrigo: this.editForm.get(['idEtatFrigo'])!.value,
      dscEtatFrigo: this.editForm.get(['dscEtatFrigo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEtatFrigo>>): void {
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
}
