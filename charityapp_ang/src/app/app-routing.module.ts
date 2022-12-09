import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router'; // CLI imports router
import { LoginPage } from './login-page.component';
import { RegisterPage } from './register-page.component';
import { StartingPage } from './starting-page.component';
import { UserPage } from './user-page.component';
import {CharityActionComponent} from "./charity-action-component/charity-action.component";

const routes: Routes =[
    { path: '', component: StartingPage },
    {path: 'login', component: LoginPage, children: [
        {
          path: 'register',
          component: RegisterPage,
        },
        {
          path: 'home',
          component: StartingPage,
        },
        {
          path: 'user',
          component: UserPage,
        }
      ],
    },

    {path: 'register', component: RegisterPage, children: [
        {
          path: 'login',
          component: LoginPage,
        },
        {
          path: 'home',
          redirectTo: '',
          component: StartingPage,
        },
        {
          path: 'user',
          component:UserPage,
        }
      ],
    },

    {path: 'charityAction', component: CharityActionComponent}
  ]; // sets up routes constant where you define your routes

// configures NgModule imports and exports
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
