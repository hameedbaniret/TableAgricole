import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAliment, Aliment } from 'app/shared/model/aliment.model';
import { AlimentService } from './aliment.service';
import { AlimentComponent } from './aliment.component';
import { AlimentDetailComponent } from './aliment-detail.component';
import { AlimentUpdateComponent } from './aliment-update.component';

@Injectable({ providedIn: 'root' })
export class AlimentResolve implements Resolve<IAliment> {
  constructor(private service: AlimentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAliment> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((aliment: HttpResponse<Aliment>) => {
          if (aliment.body) {
            return of(aliment.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Aliment());
  }
}

export const alimentRoute: Routes = [
  {
    path: '',
    component: AlimentComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.aliment.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AlimentDetailComponent,
    resolve: {
      aliment: AlimentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.aliment.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AlimentUpdateComponent,
    resolve: {
      aliment: AlimentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.aliment.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AlimentUpdateComponent,
    resolve: {
      aliment: AlimentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.aliment.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
