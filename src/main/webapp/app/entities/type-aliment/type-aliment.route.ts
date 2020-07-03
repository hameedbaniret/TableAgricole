import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypeAliment, TypeAliment } from 'app/shared/model/type-aliment.model';
import { TypeAlimentService } from './type-aliment.service';
import { TypeAlimentComponent } from './type-aliment.component';
import { TypeAlimentDetailComponent } from './type-aliment-detail.component';
import { TypeAlimentUpdateComponent } from './type-aliment-update.component';

@Injectable({ providedIn: 'root' })
export class TypeAlimentResolve implements Resolve<ITypeAliment> {
  constructor(private service: TypeAlimentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypeAliment> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typeAliment: HttpResponse<TypeAliment>) => {
          if (typeAliment.body) {
            return of(typeAliment.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TypeAliment());
  }
}

export const typeAlimentRoute: Routes = [
  {
    path: '',
    component: TypeAlimentComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.typeAliment.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypeAlimentDetailComponent,
    resolve: {
      typeAliment: TypeAlimentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.typeAliment.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypeAlimentUpdateComponent,
    resolve: {
      typeAliment: TypeAlimentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.typeAliment.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypeAlimentUpdateComponent,
    resolve: {
      typeAliment: TypeAlimentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tableAgricoleApp.typeAliment.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
