import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAliment } from 'app/shared/model/aliment.model';

@Component({
  selector: 'jhi-aliment-detail',
  templateUrl: './aliment-detail.component.html',
})
export class AlimentDetailComponent implements OnInit {
  aliment: IAliment | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ aliment }) => (this.aliment = aliment));
  }

  previousState(): void {
    window.history.back();
  }
}
