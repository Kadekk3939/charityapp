import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CharityActionComponent } from './charity-action/charity-action.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { StartingPageComponent } from './starting-page/starting-page.component';
import { UserComponent } from './user/user.component';
import { BenefactorProfileComponent } from "./benefactor-profile/benefactor-profile.component";
import { BenefactorApplicationListComponent } from "./benefactor-profile/benefactor-application-list.component";
import { BenefactorBrowseActionsComponent } from "./benefactor-profile/benefactor-browse-actions.component";
import { CharityActionDetailsComponent } from './charity-action/charity-action-details.component';
import { CharityActionApplayComponent } from './charity-action/charity-action-applay.component';
import { CharityActionSupportComponent } from './charity-action/charity-action-support.component';
import { CharityActionAplicationListComponent } from './charity-action-aplication-list/charity-action-aplication-list.component'
import { BenefactorsApplicationsComponent } from './benefactors-applications/benefactors-applications.component'
import { RateDonorsListComponent } from './rate-donors-list/rate-donors-list.component'
import { DonorsDonationHistoryComponent } from './donors-donation-history/donors-donation-history.component';
import { CharityActionRatingComponent } from './charity-action-rating/charity-action-rating.component';

const routes: Routes = [
  { path: 'welcome', component: StartingPageComponent },
  { path: '', redirectTo: '/welcome', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'charityAction', component: CharityActionComponent },
  { path: 'user', component: UserComponent },
  { path: 'benefactorProfile', component: BenefactorProfileComponent },
  { path: 'benefactorApplicationList', component: BenefactorApplicationListComponent },
  { path: 'benefactorBrowseActions', component: BenefactorBrowseActionsComponent },
  { path: 'charityAction/:name', component: CharityActionDetailsComponent },
  { path: 'charityAction/:name/applay', component: CharityActionApplayComponent },
  { path: 'charityAction/:name/support', component: CharityActionSupportComponent },
  { path: 'charityActionAplicationList', component: CharityActionAplicationListComponent },
  { path: 'benefactorsApplications', component: BenefactorsApplicationsComponent },
  { path: 'rateDonorsList', component: RateDonorsListComponent },
  { path: 'donorsDonationHistory', component: DonorsDonationHistoryComponent },
  {path:'actionRating',component:CharityActionRatingComponent},
  { path: 'actionRating/:name', component: CharityActionSupportComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
