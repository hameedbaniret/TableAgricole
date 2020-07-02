import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { TableAgricoleSharedModule } from 'app/shared/shared.module';
import { TableAgricoleCoreModule } from 'app/core/core.module';
import { TableAgricoleAppRoutingModule } from './app-routing.module';
import { TableAgricoleHomeModule } from './home/home.module';
import { TableAgricoleEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    TableAgricoleSharedModule,
    TableAgricoleCoreModule,
    TableAgricoleHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    TableAgricoleEntityModule,
    TableAgricoleAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class TableAgricoleAppModule {}
