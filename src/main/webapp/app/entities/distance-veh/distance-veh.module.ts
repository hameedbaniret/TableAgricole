import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TableAgricoleSharedModule } from 'app/shared/shared.module';
import { DistanceVehComponent } from './distance-veh.component';
import { DistanceVehDetailComponent } from './distance-veh-detail.component';
import { DistanceVehUpdateComponent } from './distance-veh-update.component';
import { DistanceVehDeleteDialogComponent } from './distance-veh-delete-dialog.component';
import { distanceVehRoute } from './distance-veh.route';

@NgModule({
  imports: [TableAgricoleSharedModule, RouterModule.forChild(distanceVehRoute)],
  declarations: [DistanceVehComponent, DistanceVehDetailComponent, DistanceVehUpdateComponent, DistanceVehDeleteDialogComponent],
  entryComponents: [DistanceVehDeleteDialogComponent],
})
export class TableAgricoleDistanceVehModule {}
