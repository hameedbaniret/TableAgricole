import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAliment } from 'app/shared/model/aliment.model';
import { AlimentService } from './aliment.service';

@Component({
  templateUrl: './aliment-delete-dialog.component.html',
})
export class AlimentDeleteDialogComponent {
  aliment?: IAliment;

  constructor(protected alimentService: AlimentService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.alimentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('alimentListModification');
      this.activeModal.close();
    });
  }
}
