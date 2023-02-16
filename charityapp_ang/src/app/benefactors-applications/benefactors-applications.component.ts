import { Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {CharityActionService} from "../charity-action/charity-action.service";
import {AppService} from "../app.service";
import {aplicationToCharityActionRead} from "../charity-action-aplication-list/aplication-to-charity-action-read";
import {benefApplicationToCharityActionRead} from "./benef-application-to-charity-action-read";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-benefactors-applications',
  templateUrl: './benefactors-applications.component.html',
  styleUrls: ['./benefactors-applications.component.css']
})
export class BenefactorsApplicationsComponent implements OnInit{
  constructor(private router: Router, private charityActionService: CharityActionService, private app: AppService) { }
  benefApplication: benefApplicationToCharityActionRead[];

  ngOnInit(): void {
    this.app.refresh();
    this.getBenefactorApplication();
  }

  getBenefactorApplication() {
    this.charityActionService.getBenefactorApplication(this.app.headers).subscribe(
      (response: benefApplicationToCharityActionRead[]) => {
        this.benefApplication = response;
        console.log(this.benefApplication);

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public logout(): void {
    localStorage.clear();
    this.router.navigateByUrl('/');
  }
}
