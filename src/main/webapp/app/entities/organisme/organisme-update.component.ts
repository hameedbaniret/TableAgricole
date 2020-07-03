import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOrganisme, Organisme } from 'app/shared/model/organisme.model';
import { OrganismeService } from './organisme.service';
import { ITerritoire } from 'app/shared/model/territoire.model';
import { TerritoireService } from 'app/entities/territoire/territoire.service';

@Component({
  selector: 'jhi-organisme-update',
  templateUrl: './organisme-update.component.html',
})
export class OrganismeUpdateComponent implements OnInit {
  isSaving = false;
  territoires: ITerritoire[] = [];

  editForm = this.fb.group({
    id: [],
    idOrganisme: [],
    nomOrganisme: [null, [Validators.required]],
    adresseOrganisme: [null, [Validators.required]],
    phoneOrganisme: [],
    courrielOrganisme: [],
    territoireId: [],
  });

  constructor(
    protected organismeService: OrganismeService,
    protected territoireService: TerritoireService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ organisme }) => {
      this.updateForm(organisme);

      this.territoireService.query().subscribe((res: HttpResponse<ITerritoire[]>) => (this.territoires = res.body || []));
    });
  }

  updateForm(organisme: IOrganisme): void {
    this.editForm.patchValue({
      id: organisme.id,
      idOrganisme: organisme.idOrganisme,
      nomOrganisme: organisme.nomOrganisme,
      adresseOrganisme: organisme.adresseOrganisme,
      phoneOrganisme: organisme.phoneOrganisme,
      courrielOrganisme: organisme.courrielOrganisme,
      territoireId: organisme.territoireId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const organisme = this.createFromForm();
    if (organisme.id !== undefined) {
      this.subscribeToSaveResponse(this.organismeService.update(organisme));
    } else {
      this.subscribeToSaveResponse(this.organismeService.create(organisme));
    }
  }

  private createFromForm(): IOrganisme {
    return {
      ...new Organisme(),
      id: this.editForm.get(['id'])!.value,
      idOrganisme: this.editForm.get(['idOrganisme'])!.value,
      nomOrganisme: this.editForm.get(['nomOrganisme'])!.value,
      adresseOrganisme: this.editForm.get(['adresseOrganisme'])!.value,
      phoneOrganisme: this.editForm.get(['phoneOrganisme'])!.value,
      courrielOrganisme: this.editForm.get(['courrielOrganisme'])!.value,
      territoireId: this.editForm.get(['territoireId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrganisme>>): void {
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

  trackById(index: number, item: ITerritoire): any {
    return item.id;
  }
}
