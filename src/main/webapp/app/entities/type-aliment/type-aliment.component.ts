import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITypeAliment } from 'app/shared/model/type-aliment.model';
import { TypeAlimentService } from './type-aliment.service';
import { TypeAlimentDeleteDialogComponent } from './type-aliment-delete-dialog.component';

@Component({
  selector: 'jhi-type-aliment',
  templateUrl: './type-aliment.component.html',
})
export class TypeAlimentComponent implements OnInit, OnDestroy {
  typeAliments?: ITypeAliment[];
  eventSubscriber?: Subscription;

  constructor(
    protected typeAlimentService: TypeAlimentService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.typeAlimentService.query().subscribe((res: HttpResponse<ITypeAliment[]>) => (this.typeAliments = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTypeAliments();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITypeAliment): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTypeAliments(): void {
    this.eventSubscriber = this.eventManager.subscribe('typeAlimentListModification', () => this.loadAll());
  }

  delete(typeAliment: ITypeAliment): void {
    const modalRef = this.modalService.open(TypeAlimentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.typeAliment = typeAliment;
  }
}
