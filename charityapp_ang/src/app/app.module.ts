import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { UserService } from './user.service';
import { StartingPage } from './starting-page.component';
import { LoginPage } from './login-page.component';

import { AppRoutingModule } from './app-routing.module'; // CLI imports AppRoutingModule
import { RegisterPage } from './register-page.component';
import { AppService } from './app.services';
import { CharityActionComponent } from './charity-action-component/charity-action.component';

@NgModule({
  declarations: [
    AppComponent,
    StartingPage,
    LoginPage,
    RegisterPage,
    CharityActionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, // CLI adds AppRoutingModule to the AppModule's imports array
    HttpClientModule,
    FormsModule
  ],
  providers: [AppService],
  bootstrap: [AppComponent]
})
export class AppModule { }


