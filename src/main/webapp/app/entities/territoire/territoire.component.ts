import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITerritoire } from 'app/shared/model/territoire.model';
import { TerritoireService } from './territoire.service';
import { TerritoireDeleteDialogComponent } from './territoire-delete-dialog.component';

@Component({
  selector: 'jhi-territoire',
  templateUrl: './territoire.component.html',
})
export class TerritoireComponent implements OnInit, OnDestroy {
  territoires?: ITerritoire[];
  eventSubscriber?: Subscription;

  constructor(protected territoireService: TerritoireService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.territoireService.query().subscribe((res: HttpResponse<ITerritoire[]>) => (this.territoires = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTerritoires();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITerritoire): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTerritoires(): void {
    this.eventSubscriber = this.eventManager.subscribe('territoireListModification', () => this.loadAll());
  }

  delete(territoire: ITerritoire): void {
    const modalRef = this.modalService.open(TerritoireDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.territoire = territoire;
  }
}
