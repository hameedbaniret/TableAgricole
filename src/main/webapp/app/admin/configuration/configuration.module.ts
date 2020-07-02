import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TableAgricoleSharedModule } from 'app/shared/shared.module';

import { ConfigurationComponent } from './configuration.component';

import { configurationRoute } from './configuration.route';

@NgModule({
  imports: [TableAgricoleSharedModule, RouterModule.forChild([configurationRoute])],
  declarations: [ConfigurationComponent],
})
export class ConfigurationModule {}
