import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StartingPageComponent } from './starting-page/starting-page.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UserComponent } from './user/user.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CharityActionComponent } from './charity-action/charity-action.component';
import { AppService } from './app.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatRadioModule } from '@angular/material/radio';
import {CharityActionService} from "./charity-action/charity-action.service";
import { BenefactorProfileComponent } from './benefactor-profile/benefactor-profile.component';
import { BenefactorApplicationListComponent } from './benefactor-profile/benefactor-application-list.component';
import { BenefactorBrowseActionsComponent } from './benefactor-profile/benefactor-browse-actions.component';
import { RouterModule } from '@angular/router';
import { CharityActionDetailsComponent } from './charity-action/charity-action-details.component';
import {CharityActionApplyComponent} from './charity-action/charity-action-apply.component'

@NgModule({
  declarations: [
    AppComponent,
    StartingPageComponent,
    LoginComponent,
    RegisterComponent,
    UserComponent,
    CharityActionComponent,
    BenefactorProfileComponent,
    BenefactorApplicationListComponent,
    BenefactorBrowseActionsComponent,
    CharityActionDetailsComponent,
    CharityActionApplyComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule  ,
    MatRadioModule,
    RouterModule
  ],
  providers: [
    AppService,
    CharityActionService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
