import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAliment } from 'app/shared/model/aliment.model';
import { AlimentService } from './aliment.service';
import { AlimentDeleteDialogComponent } from './aliment-delete-dialog.component';

@Component({
  selector: 'jhi-aliment',
  templateUrl: './aliment.component.html',
})
export class AlimentComponent implements OnInit, OnDestroy {
  aliments?: IAliment[];
  eventSubscriber?: Subscription;

  constructor(protected alimentService: AlimentService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.alimentService.query().subscribe((res: HttpResponse<IAliment[]>) => (this.aliments = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAliments();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAliment): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAliments(): void {
    this.eventSubscriber = this.eventManager.subscribe('alimentListModification', () => this.loadAll());
  }

  delete(aliment: IAliment): void {
    const modalRef = this.modalService.open(AlimentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.aliment = aliment;
  }
}
