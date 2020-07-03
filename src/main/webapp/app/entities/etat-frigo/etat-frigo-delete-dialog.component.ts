import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEtatFrigo } from 'app/shared/model/etat-frigo.model';
import { EtatFrigoService } from './etat-frigo.service';

@Component({
  templateUrl: './etat-frigo-delete-dialog.component.html',
})
export class EtatFrigoDeleteDialogComponent {
  etatFrigo?: IEtatFrigo;

  constructor(protected etatFrigoService: EtatFrigoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.etatFrigoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('etatFrigoListModification');
      this.activeModal.close();
    });
  }
}
