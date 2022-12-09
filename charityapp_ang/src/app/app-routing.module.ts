import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CharityActionComponent } from './charity-action/charity-action.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { StartingPageComponent } from './starting-page/starting-page.component';
import { UserComponent } from './user/user.component';

const routes: Routes = [
  {path: 'welcome', component: StartingPageComponent},
  {path: '',redirectTo:'/welcome' ,pathMatch:'full'},
  {path: 'login', component: LoginComponent},
  {path: 'register', component:RegisterComponent},
  {path: 'charityAction', component: CharityActionComponent},
  {path: 'user',component:UserComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
