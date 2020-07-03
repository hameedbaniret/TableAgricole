import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDistanceVeh, DistanceVeh } from 'app/shared/model/distance-veh.model';
import { DistanceVehService } from './distance-veh.service';
import { DistanceVehComponent } from './distance-veh.component';
import { DistanceVehDetailComponent } from './distance-veh-detail.component';
import { DistanceVehUpdateComponent } from './distance-veh-update.component';

@Injectable({ providedIn: 'root' })
export class DistanceVehResolve implements Resolve<IDistanceVeh> {
  constructor(private service: DistanceVehService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDistanceVeh> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((distanceVeh: HttpResponse<DistanceVeh>) => {
          if (distanceVeh.body) {
            return of(distanceVeh.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DistanceVeh());
  }
}

export const distanceVehRoute: Routes = [
  {
    path: '',
    component: DistanceVehComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.distanceVeh.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DistanceVehDetailComponent,
    resolve: {
      distanceVeh: DistanceVehResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.distanceVeh.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DistanceVehUpdateComponent,
    resolve: {
      distanceVeh: DistanceVehResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.distanceVeh.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DistanceVehUpdateComponent,
    resolve: {
      distanceVeh: DistanceVehResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.distanceVeh.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
