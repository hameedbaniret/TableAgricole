import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEtatFrigo } from 'app/shared/model/etat-frigo.model';
import { EtatFrigoService } from './etat-frigo.service';
import { EtatFrigoDeleteDialogComponent } from './etat-frigo-delete-dialog.component';

@Component({
  selector: 'jhi-etat-frigo',
  templateUrl: './etat-frigo.component.html',
})
export class EtatFrigoComponent implements OnInit, OnDestroy {
  etatFrigos?: IEtatFrigo[];
  eventSubscriber?: Subscription;

  constructor(protected etatFrigoService: EtatFrigoService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.etatFrigoService.query().subscribe((res: HttpResponse<IEtatFrigo[]>) => (this.etatFrigos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEtatFrigos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEtatFrigo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEtatFrigos(): void {
    this.eventSubscriber = this.eventManager.subscribe('etatFrigoListModification', () => this.loadAll());
  }

  delete(etatFrigo: IEtatFrigo): void {
    const modalRef = this.modalService.open(EtatFrigoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.etatFrigo = etatFrigo;
  }
}
