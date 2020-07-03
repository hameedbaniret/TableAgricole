import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAliment, Aliment } from 'app/shared/model/aliment.model';
import { AlimentService } from './aliment.service';
import { ITypeAliment } from 'app/shared/model/type-aliment.model';
import { TypeAlimentService } from 'app/entities/type-aliment/type-aliment.service';

@Component({
  selector: 'jhi-aliment-update',
  templateUrl: './aliment-update.component.html',
})
export class AlimentUpdateComponent implements OnInit {
  isSaving = false;
  typealiments: ITypeAliment[] = [];

  editForm = this.fb.group({
    id: [],
    idAliment: [],
    dscAliment: [null, [Validators.required]],
    typeAlimentId: [],
  });

  constructor(
    protected alimentService: AlimentService,
    protected typeAlimentService: TypeAlimentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ aliment }) => {
      this.updateForm(aliment);

      this.typeAlimentService.query().subscribe((res: HttpResponse<ITypeAliment[]>) => (this.typealiments = res.body || []));
    });
  }

  updateForm(aliment: IAliment): void {
    this.editForm.patchValue({
      id: aliment.id,
      idAliment: aliment.idAliment,
      dscAliment: aliment.dscAliment,
      typeAlimentId: aliment.typeAlimentId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const aliment = this.createFromForm();
    if (aliment.id !== undefined) {
      this.subscribeToSaveResponse(this.alimentService.update(aliment));
    } else {
      this.subscribeToSaveResponse(this.alimentService.create(aliment));
    }
  }

  private createFromForm(): IAliment {
    return {
      ...new Aliment(),
      id: this.editForm.get(['id'])!.value,
      idAliment: this.editForm.get(['idAliment'])!.value,
      dscAliment: this.editForm.get(['dscAliment'])!.value,
      typeAlimentId: this.editForm.get(['typeAlimentId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAliment>>): void {
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

  trackById(index: number, item: ITypeAliment): any {
    return item.id;
  }
}
