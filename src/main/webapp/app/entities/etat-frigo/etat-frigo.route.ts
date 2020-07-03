import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEtatFrigo, EtatFrigo } from 'app/shared/model/etat-frigo.model';
import { EtatFrigoService } from './etat-frigo.service';
import { EtatFrigoComponent } from './etat-frigo.component';
import { EtatFrigoDetailComponent } from './etat-frigo-detail.component';
import { EtatFrigoUpdateComponent } from './etat-frigo-update.component';

@Injectable({ providedIn: 'root' })
export class EtatFrigoResolve implements Resolve<IEtatFrigo> {
  constructor(private service: EtatFrigoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEtatFrigo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((etatFrigo: HttpResponse<EtatFrigo>) => {
          if (etatFrigo.body) {
            return of(etatFrigo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EtatFrigo());
  }
}

export const etatFrigoRoute: Routes = [
  {
    path: '',
    component: EtatFrigoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.etatFrigo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EtatFrigoDetailComponent,
    resolve: {
      etatFrigo: EtatFrigoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.etatFrigo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EtatFrigoUpdateComponent,
    resolve: {
      etatFrigo: EtatFrigoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.etatFrigo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EtatFrigoUpdateComponent,
    resolve: {
      etatFrigo: EtatFrigoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.etatFrigo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
