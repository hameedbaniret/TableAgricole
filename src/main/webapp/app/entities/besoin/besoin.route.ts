import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBesoin, Besoin } from 'app/shared/model/besoin.model';
import { BesoinService } from './besoin.service';
import { BesoinComponent } from './besoin.component';
import { BesoinDetailComponent } from './besoin-detail.component';
import { BesoinUpdateComponent } from './besoin-update.component';

@Injectable({ providedIn: 'root' })
export class BesoinResolve implements Resolve<IBesoin> {
  constructor(private service: BesoinService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBesoin> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((besoin: HttpResponse<Besoin>) => {
          if (besoin.body) {
            return of(besoin.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Besoin());
  }
}

export const besoinRoute: Routes = [
  {
    path: '',
    component: BesoinComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tableAgricoleApp.besoin.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BesoinDetailComponent,
    resolve: {
      besoin: BesoinResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.besoin.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BesoinUpdateComponent,
    resolve: {
      besoin: BesoinResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.besoin.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BesoinUpdateComponent,
    resolve: {
      besoin: BesoinResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.besoin.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
