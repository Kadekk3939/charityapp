import { LOCALE_ID,NgModule } from '@angular/core';
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
import { CharityActionService } from "./charity-action/charity-action.service";
import { BenefactorProfileComponent } from './benefactor-profile/benefactor-profile.component';
import { BenefactorApplicationListComponent } from './benefactor-profile/benefactor-application-list.component';
import { BenefactorBrowseActionsComponent } from './benefactor-profile/benefactor-browse-actions.component';
import { RouterModule } from '@angular/router';
import { CharityActionDetailsComponent } from './charity-action/charity-action-details.component';
import { CharityActionApplayComponent } from './charity-action/charity-action-applay.component'
import { CharityActionSupportComponent } from './charity-action/charity-action-support.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule, CurrencyPipe} from '@angular/common';
import { CharityActionAplicationListComponent } from './charity-action-aplication-list/charity-action-aplication-list.component';
import { BenefactorsApplicationsComponent } from './benefactors-applications/benefactors-applications.component';
import { RateDonorsListComponent } from './rate-donors-list/rate-donors-list.component';
import { DonorsDonationHistoryComponent } from './donors-donation-history/donors-donation-history.component';
import { DonationService } from './donors-donation-history/donation.service';
import { CharityActionRatingComponent } from './charity-action-rating/charity-action-rating.component';
import { CharityActionRateComponent } from './charity-action-rating/rate-charity-action.component';
import { DonorRateComponent } from './rate-donors-list/rate-donor.component';

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
    CharityActionApplayComponent,
    CharityActionSupportComponent,
    CharityActionAplicationListComponent,
    BenefactorsApplicationsComponent,
    RateDonorsListComponent,
    DonorsDonationHistoryComponent,
    CharityActionRatingComponent,
    CharityActionRateComponent,
    DonorRateComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatRadioModule,
    RouterModule,
    ReactiveFormsModule,
  ],
  providers: [
    AppService,
    CharityActionService,
    DonationService,
    CurrencyPipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
