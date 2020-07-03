import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDistanceVeh, DistanceVeh } from 'app/shared/model/distance-veh.model';
import { DistanceVehService } from './distance-veh.service';

@Component({
  selector: 'jhi-distance-veh-update',
  templateUrl: './distance-veh-update.component.html',
})
export class DistanceVehUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idDistanceVeh: [],
    dscDistanceVeh: [null, [Validators.required]],
  });

  constructor(protected distanceVehService: DistanceVehService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ distanceVeh }) => {
      this.updateForm(distanceVeh);
    });
  }

  updateForm(distanceVeh: IDistanceVeh): void {
    this.editForm.patchValue({
      id: distanceVeh.id,
      idDistanceVeh: distanceVeh.idDistanceVeh,
      dscDistanceVeh: distanceVeh.dscDistanceVeh,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const distanceVeh = this.createFromForm();
    if (distanceVeh.id !== undefined) {
      this.subscribeToSaveResponse(this.distanceVehService.update(distanceVeh));
    } else {
      this.subscribeToSaveResponse(this.distanceVehService.create(distanceVeh));
    }
  }

  private createFromForm(): IDistanceVeh {
    return {
      ...new DistanceVeh(),
      id: this.editForm.get(['id'])!.value,
      idDistanceVeh: this.editForm.get(['idDistanceVeh'])!.value,
      dscDistanceVeh: this.editForm.get(['dscDistanceVeh'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDistanceVeh>>): void {
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
