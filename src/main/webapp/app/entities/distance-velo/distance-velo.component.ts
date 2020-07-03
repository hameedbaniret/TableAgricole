import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDistanceVelo } from 'app/shared/model/distance-velo.model';
import { DistanceVeloService } from './distance-velo.service';
import { DistanceVeloDeleteDialogComponent } from './distance-velo-delete-dialog.component';

@Component({
  selector: 'jhi-distance-velo',
  templateUrl: './distance-velo.component.html',
})
export class DistanceVeloComponent implements OnInit, OnDestroy {
  distanceVelos?: IDistanceVelo[];
  eventSubscriber?: Subscription;

  constructor(
    protected distanceVeloService: DistanceVeloService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.distanceVeloService.query().subscribe((res: HttpResponse<IDistanceVelo[]>) => (this.distanceVelos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDistanceVelos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDistanceVelo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDistanceVelos(): void {
    this.eventSubscriber = this.eventManager.subscribe('distanceVeloListModification', () => this.loadAll());
  }

  delete(distanceVelo: IDistanceVelo): void {
    const modalRef = this.modalService.open(DistanceVeloDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.distanceVelo = distanceVelo;
  }
}
