import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router'; // CLI imports router
import { LoginPage } from './login-page.component';
import { StartingPage } from './starting-page.component';

const routes: Routes =[
    { path: '', component: StartingPage },
    { path: 'login', component: LoginPage },
  ]; // sets up routes constant where you define your routes

// configures NgModule imports and exports
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }