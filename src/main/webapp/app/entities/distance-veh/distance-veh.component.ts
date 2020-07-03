import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDistanceVeh } from 'app/shared/model/distance-veh.model';
import { DistanceVehService } from './distance-veh.service';
import { DistanceVehDeleteDialogComponent } from './distance-veh-delete-dialog.component';

@Component({
  selector: 'jhi-distance-veh',
  templateUrl: './distance-veh.component.html',
})
export class DistanceVehComponent implements OnInit, OnDestroy {
  distanceVehs?: IDistanceVeh[];
  eventSubscriber?: Subscription;

  constructor(
    protected distanceVehService: DistanceVehService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.distanceVehService.query().subscribe((res: HttpResponse<IDistanceVeh[]>) => (this.distanceVehs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDistanceVehs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDistanceVeh): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDistanceVehs(): void {
    this.eventSubscriber = this.eventManager.subscribe('distanceVehListModification', () => this.loadAll());
  }

  delete(distanceVeh: IDistanceVeh): void {
    const modalRef = this.modalService.open(DistanceVehDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.distanceVeh = distanceVeh;
  }
}
