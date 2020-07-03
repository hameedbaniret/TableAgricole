import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEtatFrigo } from 'app/shared/model/etat-frigo.model';

@Component({
  selector: 'jhi-etat-frigo-detail',
  templateUrl: './etat-frigo-detail.component.html',
})
export class EtatFrigoDetailComponent implements OnInit {
  etatFrigo: IEtatFrigo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ etatFrigo }) => (this.etatFrigo = etatFrigo));
  }

  previousState(): void {
    window.history.back();
  }
}
