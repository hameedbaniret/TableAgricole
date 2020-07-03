import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TableAgricoleSharedModule } from 'app/shared/shared.module';
import { AlimentComponent } from './aliment.component';
import { AlimentDetailComponent } from './aliment-detail.component';
import { AlimentUpdateComponent } from './aliment-update.component';
import { AlimentDeleteDialogComponent } from './aliment-delete-dialog.component';
import { alimentRoute } from './aliment.route';

@NgModule({
  imports: [TableAgricoleSharedModule, RouterModule.forChild(alimentRoute)],
  declarations: [AlimentComponent, AlimentDetailComponent, AlimentUpdateComponent, AlimentDeleteDialogComponent],
  entryComponents: [AlimentDeleteDialogComponent],
})
export class TableAgricoleAlimentModule {}
