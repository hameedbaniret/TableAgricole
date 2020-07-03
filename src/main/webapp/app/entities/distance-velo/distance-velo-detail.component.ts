import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDistanceVelo } from 'app/shared/model/distance-velo.model';

@Component({
  selector: 'jhi-distance-velo-detail',
  templateUrl: './distance-velo-detail.component.html',
})
export class DistanceVeloDetailComponent implements OnInit {
  distanceVelo: IDistanceVelo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ distanceVelo }) => (this.distanceVelo = distanceVelo));
  }

  previousState(): void {
    window.history.back();
  }
}
