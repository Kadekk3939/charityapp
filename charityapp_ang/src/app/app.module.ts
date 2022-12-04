import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { UserService } from './user.service';
import { StartingPage } from './starting-page.component';
import { LoginPage } from './login-page.component';

import { AppRoutingModule } from './app-routing.module'; // CLI imports AppRoutingModule

@NgModule({
  declarations: [
    AppComponent,
    StartingPage,
    LoginPage
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, // CLI adds AppRoutingModule to the AppModule's imports array
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }


