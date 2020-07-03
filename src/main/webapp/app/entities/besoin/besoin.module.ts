import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TableAgricoleSharedModule } from 'app/shared/shared.module';
import { BesoinComponent } from './besoin.component';
import { BesoinDetailComponent } from './besoin-detail.component';
import { BesoinUpdateComponent } from './besoin-update.component';
import { BesoinDeleteDialogComponent } from './besoin-delete-dialog.component';
import { besoinRoute } from './besoin.route';

@NgModule({
  imports: [TableAgricoleSharedModule, RouterModule.forChild(besoinRoute)],
  declarations: [BesoinComponent, BesoinDetailComponent, BesoinUpdateComponent, BesoinDeleteDialogComponent],
  entryComponents: [BesoinDeleteDialogComponent],
})
export class TableAgricoleBesoinModule {}
