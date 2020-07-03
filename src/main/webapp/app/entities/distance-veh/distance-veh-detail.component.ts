import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDistanceVeh } from 'app/shared/model/distance-veh.model';

@Component({
  selector: 'jhi-distance-veh-detail',
  templateUrl: './distance-veh-detail.component.html',
})
export class DistanceVehDetailComponent implements OnInit {
  distanceVeh: IDistanceVeh | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ distanceVeh }) => (this.distanceVeh = distanceVeh));
  }

  previousState(): void {
    window.history.back();
  }
}
