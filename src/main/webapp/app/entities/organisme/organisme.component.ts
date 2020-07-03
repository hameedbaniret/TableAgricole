import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOrganisme } from 'app/shared/model/organisme.model';
import { OrganismeService } from './organisme.service';
import { OrganismeDeleteDialogComponent } from './organisme-delete-dialog.component';

@Component({
  selector: 'jhi-organisme',
  templateUrl: './organisme.component.html',
})
export class OrganismeComponent implements OnInit, OnDestroy {
  organismes?: IOrganisme[];
  eventSubscriber?: Subscription;

  constructor(protected organismeService: OrganismeService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.organismeService.query().subscribe((res: HttpResponse<IOrganisme[]>) => (this.organismes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOrganismes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOrganisme): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOrganismes(): void {
    this.eventSubscriber = this.eventManager.subscribe('organismeListModification', () => this.loadAll());
  }

  delete(organisme: IOrganisme): void {
    const modalRef = this.modalService.open(OrganismeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.organisme = organisme;
  }
}
