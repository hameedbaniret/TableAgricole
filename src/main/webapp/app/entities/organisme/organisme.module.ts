import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TableAgricoleSharedModule } from 'app/shared/shared.module';
import { OrganismeComponent } from './organisme.component';
import { OrganismeDetailComponent } from './organisme-detail.component';
import { OrganismeUpdateComponent } from './organisme-update.component';
import { OrganismeDeleteDialogComponent } from './organisme-delete-dialog.component';
import { organismeRoute } from './organisme.route';

@NgModule({
  imports: [TableAgricoleSharedModule, RouterModule.forChild(organismeRoute)],
  declarations: [OrganismeComponent, OrganismeDetailComponent, OrganismeUpdateComponent, OrganismeDeleteDialogComponent],
  entryComponents: [OrganismeDeleteDialogComponent],
})
export class TableAgricoleOrganismeModule {}
