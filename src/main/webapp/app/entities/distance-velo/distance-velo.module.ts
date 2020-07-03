import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TableAgricoleSharedModule } from 'app/shared/shared.module';
import { DistanceVeloComponent } from './distance-velo.component';
import { DistanceVeloDetailComponent } from './distance-velo-detail.component';
import { DistanceVeloUpdateComponent } from './distance-velo-update.component';
import { DistanceVeloDeleteDialogComponent } from './distance-velo-delete-dialog.component';
import { distanceVeloRoute } from './distance-velo.route';

@NgModule({
  imports: [TableAgricoleSharedModule, RouterModule.forChild(distanceVeloRoute)],
  declarations: [DistanceVeloComponent, DistanceVeloDetailComponent, DistanceVeloUpdateComponent, DistanceVeloDeleteDialogComponent],
  entryComponents: [DistanceVeloDeleteDialogComponent],
})
export class TableAgricoleDistanceVeloModule {}
