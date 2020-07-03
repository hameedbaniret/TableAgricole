import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TableAgricoleSharedModule } from 'app/shared/shared.module';
import { TypeAlimentComponent } from './type-aliment.component';
import { TypeAlimentDetailComponent } from './type-aliment-detail.component';
import { TypeAlimentUpdateComponent } from './type-aliment-update.component';
import { TypeAlimentDeleteDialogComponent } from './type-aliment-delete-dialog.component';
import { typeAlimentRoute } from './type-aliment.route';

@NgModule({
  imports: [TableAgricoleSharedModule, RouterModule.forChild(typeAlimentRoute)],
  declarations: [TypeAlimentComponent, TypeAlimentDetailComponent, TypeAlimentUpdateComponent, TypeAlimentDeleteDialogComponent],
  entryComponents: [TypeAlimentDeleteDialogComponent],
})
export class TableAgricoleTypeAlimentModule {}
