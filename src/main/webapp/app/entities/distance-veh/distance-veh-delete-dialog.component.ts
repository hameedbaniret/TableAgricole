import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDistanceVeh } from 'app/shared/model/distance-veh.model';
import { DistanceVehService } from './distance-veh.service';

@Component({
  templateUrl: './distance-veh-delete-dialog.component.html',
})
export class DistanceVehDeleteDialogComponent {
  distanceVeh?: IDistanceVeh;

  constructor(
    protected distanceVehService: DistanceVehService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.distanceVehService.delete(id).subscribe(() => {
      this.eventManager.broadcast('distanceVehListModification');
      this.activeModal.close();
    });
  }
}
