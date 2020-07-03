import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDistanceVelo, DistanceVelo } from 'app/shared/model/distance-velo.model';
import { DistanceVeloService } from './distance-velo.service';
import { DistanceVeloComponent } from './distance-velo.component';
import { DistanceVeloDetailComponent } from './distance-velo-detail.component';
import { DistanceVeloUpdateComponent } from './distance-velo-update.component';

@Injectable({ providedIn: 'root' })
export class DistanceVeloResolve implements Resolve<IDistanceVelo> {
  constructor(private service: DistanceVeloService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDistanceVelo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((distanceVelo: HttpResponse<DistanceVelo>) => {
          if (distanceVelo.body) {
            return of(distanceVelo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DistanceVelo());
  }
}

export const distanceVeloRoute: Routes = [
  {
    path: '',
    component: DistanceVeloComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.distanceVelo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DistanceVeloDetailComponent,
    resolve: {
      distanceVelo: DistanceVeloResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.distanceVelo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DistanceVeloUpdateComponent,
    resolve: {
      distanceVelo: DistanceVeloResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.distanceVelo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DistanceVeloUpdateComponent,
    resolve: {
      distanceVelo: DistanceVeloResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.distanceVelo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
