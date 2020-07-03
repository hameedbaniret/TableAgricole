import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDistanceVelo, DistanceVelo } from 'app/shared/model/distance-velo.model';
import { DistanceVeloService } from './distance-velo.service';

@Component({
  selector: 'jhi-distance-velo-update',
  templateUrl: './distance-velo-update.component.html',
})
export class DistanceVeloUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idDistanceVelo: [],
    dscDistanceVelo: [null, [Validators.required]],
  });

  constructor(protected distanceVeloService: DistanceVeloService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ distanceVelo }) => {
      this.updateForm(distanceVelo);
    });
  }

  updateForm(distanceVelo: IDistanceVelo): void {
    this.editForm.patchValue({
      id: distanceVelo.id,
      idDistanceVelo: distanceVelo.idDistanceVelo,
      dscDistanceVelo: distanceVelo.dscDistanceVelo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const distanceVelo = this.createFromForm();
    if (distanceVelo.id !== undefined) {
      this.subscribeToSaveResponse(this.distanceVeloService.update(distanceVelo));
    } else {
      this.subscribeToSaveResponse(this.distanceVeloService.create(distanceVelo));
    }
  }

  private createFromForm(): IDistanceVelo {
    return {
      ...new DistanceVelo(),
      id: this.editForm.get(['id'])!.value,
      idDistanceVelo: this.editForm.get(['idDistanceVelo'])!.value,
      dscDistanceVelo: this.editForm.get(['dscDistanceVelo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDistanceVelo>>): void {
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
