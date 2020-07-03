import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITerritoire, Territoire } from 'app/shared/model/territoire.model';
import { TerritoireService } from './territoire.service';
import { TerritoireComponent } from './territoire.component';
import { TerritoireDetailComponent } from './territoire-detail.component';
import { TerritoireUpdateComponent } from './territoire-update.component';

@Injectable({ providedIn: 'root' })
export class TerritoireResolve implements Resolve<ITerritoire> {
  constructor(private service: TerritoireService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITerritoire> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((territoire: HttpResponse<Territoire>) => {
          if (territoire.body) {
            return of(territoire.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Territoire());
  }
}

export const territoireRoute: Routes = [
  {
    path: '',
    component: TerritoireComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.territoire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TerritoireDetailComponent,
    resolve: {
      territoire: TerritoireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.territoire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TerritoireUpdateComponent,
    resolve: {
      territoire: TerritoireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.territoire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TerritoireUpdateComponent,
    resolve: {
      territoire: TerritoireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.territoire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
