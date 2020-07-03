import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITerritoire, Territoire } from 'app/shared/model/territoire.model';
import { TerritoireService } from './territoire.service';

@Component({
  selector: 'jhi-territoire-update',
  templateUrl: './territoire-update.component.html',
})
export class TerritoireUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idTerritoire: [],
    dscTerritoire: [null, [Validators.required]],
  });

  constructor(protected territoireService: TerritoireService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ territoire }) => {
      this.updateForm(territoire);
    });
  }

  updateForm(territoire: ITerritoire): void {
    this.editForm.patchValue({
      id: territoire.id,
      idTerritoire: territoire.idTerritoire,
      dscTerritoire: territoire.dscTerritoire,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const territoire = this.createFromForm();
    if (territoire.id !== undefined) {
      this.subscribeToSaveResponse(this.territoireService.update(territoire));
    } else {
      this.subscribeToSaveResponse(this.territoireService.create(territoire));
    }
  }

  private createFromForm(): ITerritoire {
    return {
      ...new Territoire(),
      id: this.editForm.get(['id'])!.value,
      idTerritoire: this.editForm.get(['idTerritoire'])!.value,
      dscTerritoire: this.editForm.get(['dscTerritoire'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITerritoire>>): void {
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
