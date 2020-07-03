import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITerritoire } from 'app/shared/model/territoire.model';
import { TerritoireService } from './territoire.service';

@Component({
  templateUrl: './territoire-delete-dialog.component.html',
})
export class TerritoireDeleteDialogComponent {
  territoire?: ITerritoire;

  constructor(
    protected territoireService: TerritoireService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.territoireService.delete(id).subscribe(() => {
      this.eventManager.broadcast('territoireListModification');
      this.activeModal.close();
    });
  }
}
