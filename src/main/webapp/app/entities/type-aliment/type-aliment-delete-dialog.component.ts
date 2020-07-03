import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeAliment } from 'app/shared/model/type-aliment.model';
import { TypeAlimentService } from './type-aliment.service';

@Component({
  templateUrl: './type-aliment-delete-dialog.component.html',
})
export class TypeAlimentDeleteDialogComponent {
  typeAliment?: ITypeAliment;

  constructor(
    protected typeAlimentService: TypeAlimentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typeAlimentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typeAlimentListModification');
      this.activeModal.close();
    });
  }
}
