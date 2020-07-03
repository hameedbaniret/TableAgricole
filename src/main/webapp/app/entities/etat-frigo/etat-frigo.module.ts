import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TableAgricoleSharedModule } from 'app/shared/shared.module';
import { EtatFrigoComponent } from './etat-frigo.component';
import { EtatFrigoDetailComponent } from './etat-frigo-detail.component';
import { EtatFrigoUpdateComponent } from './etat-frigo-update.component';
import { EtatFrigoDeleteDialogComponent } from './etat-frigo-delete-dialog.component';
import { etatFrigoRoute } from './etat-frigo.route';

@NgModule({
  imports: [TableAgricoleSharedModule, RouterModule.forChild(etatFrigoRoute)],
  declarations: [EtatFrigoComponent, EtatFrigoDetailComponent, EtatFrigoUpdateComponent, EtatFrigoDeleteDialogComponent],
  entryComponents: [EtatFrigoDeleteDialogComponent],
})
export class TableAgricoleEtatFrigoModule {}
