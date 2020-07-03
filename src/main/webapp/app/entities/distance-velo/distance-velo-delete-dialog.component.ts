import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDistanceVelo } from 'app/shared/model/distance-velo.model';
import { DistanceVeloService } from './distance-velo.service';

@Component({
  templateUrl: './distance-velo-delete-dialog.component.html',
})
export class DistanceVeloDeleteDialogComponent {
  distanceVelo?: IDistanceVelo;

  constructor(
    protected distanceVeloService: DistanceVeloService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.distanceVeloService.delete(id).subscribe(() => {
      this.eventManager.broadcast('distanceVeloListModification');
      this.activeModal.close();
    });
  }
}
