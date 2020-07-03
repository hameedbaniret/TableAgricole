import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypeAliment, TypeAliment } from 'app/shared/model/type-aliment.model';
import { TypeAlimentService } from './type-aliment.service';

@Component({
  selector: 'jhi-type-aliment-update',
  templateUrl: './type-aliment-update.component.html',
})
export class TypeAlimentUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idTypeAliment: [],
    dscTypeAliment: [null, [Validators.required]],
  });

  constructor(protected typeAlimentService: TypeAlimentService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeAliment }) => {
      this.updateForm(typeAliment);
    });
  }

  updateForm(typeAliment: ITypeAliment): void {
    this.editForm.patchValue({
      id: typeAliment.id,
      idTypeAliment: typeAliment.idTypeAliment,
      dscTypeAliment: typeAliment.dscTypeAliment,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typeAliment = this.createFromForm();
    if (typeAliment.id !== undefined) {
      this.subscribeToSaveResponse(this.typeAlimentService.update(typeAliment));
    } else {
      this.subscribeToSaveResponse(this.typeAlimentService.create(typeAliment));
    }
  }

  private createFromForm(): ITypeAliment {
    return {
      ...new TypeAliment(),
      id: this.editForm.get(['id'])!.value,
      idTypeAliment: this.editForm.get(['idTypeAliment'])!.value,
      dscTypeAliment: this.editForm.get(['dscTypeAliment'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeAliment>>): void {
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
