import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITerritoire } from 'app/shared/model/territoire.model';

@Component({
  selector: 'jhi-territoire-detail',
  templateUrl: './territoire-detail.component.html',
})
export class TerritoireDetailComponent implements OnInit {
  territoire: ITerritoire | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ territoire }) => (this.territoire = territoire));
  }

  previousState(): void {
    window.history.back();
  }
}
