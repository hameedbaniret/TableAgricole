import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBesoin } from 'app/shared/model/besoin.model';
import { BesoinService } from './besoin.service';

@Component({
  templateUrl: './besoin-delete-dialog.component.html',
})
export class BesoinDeleteDialogComponent {
  besoin?: IBesoin;

  constructor(protected besoinService: BesoinService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.besoinService.delete(id).subscribe(() => {
      this.eventManager.broadcast('besoinListModification');
      this.activeModal.close();
    });
  }
}
