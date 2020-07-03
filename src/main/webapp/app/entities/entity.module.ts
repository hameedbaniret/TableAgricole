import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'territoire',
        loadChildren: () => import('./territoire/territoire.module').then(m => m.TableAgricoleTerritoireModule),
      },
      {
        path: 'type-aliment',
        loadChildren: () => import('./type-aliment/type-aliment.module').then(m => m.TableAgricoleTypeAlimentModule),
      },
      {
        path: 'aliment',
        loadChildren: () => import('./aliment/aliment.module').then(m => m.TableAgricoleAlimentModule),
      },
      {
        path: 'organisme',
        loadChildren: () => import('./organisme/organisme.module').then(m => m.TableAgricoleOrganismeModule),
      },
      {
        path: 'besoin',
        loadChildren: () => import('./besoin/besoin.module').then(m => m.TableAgricoleBesoinModule),
      },
      {
        path: 'distance-veh',
        loadChildren: () => import('./distance-veh/distance-veh.module').then(m => m.TableAgricoleDistanceVehModule),
      },
      {
        path: 'distance-velo',
        loadChildren: () => import('./distance-velo/distance-velo.module').then(m => m.TableAgricoleDistanceVeloModule),
      },
      {
        path: 'etat-frigo',
        loadChildren: () => import('./etat-frigo/etat-frigo.module').then(m => m.TableAgricoleEtatFrigoModule),
      },
      {
        path: 'demande',
        loadChildren: () => import('./demande/demande.module').then(m => m.TableAgricoleDemandeModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class TableAgricoleEntityModule {}
