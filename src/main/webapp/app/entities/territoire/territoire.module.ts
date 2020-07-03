import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TableAgricoleSharedModule } from 'app/shared/shared.module';
import { TerritoireComponent } from './territoire.component';
import { TerritoireDetailComponent } from './territoire-detail.component';
import { TerritoireUpdateComponent } from './territoire-update.component';
import { TerritoireDeleteDialogComponent } from './territoire-delete-dialog.component';
import { territoireRoute } from './territoire.route';

@NgModule({
  imports: [TableAgricoleSharedModule, RouterModule.forChild(territoireRoute)],
  declarations: [TerritoireComponent, TerritoireDetailComponent, TerritoireUpdateComponent, TerritoireDeleteDialogComponent],
  entryComponents: [TerritoireDeleteDialogComponent],
})
export class TableAgricoleTerritoireModule {}
