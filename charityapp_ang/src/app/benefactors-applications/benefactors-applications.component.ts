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

  public benefApplication: benefApplicationToCharityActionRead|undefined;
  public aplicationId:number;
  public userLogin:string;

  ngOnInit(): void {
    this.app.refresh();
    this.getBenefactorApplication();
    
  }

  getBenefactorApplication() {
    this.charityActionService.getBenefactorApplication(this.app.headers).subscribe(
      (response: number) => {
        this.aplicationId = response;
        console.log(this.aplicationId);
        this.getBenefactoAplicationToVerify();

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  getBenefactoAplicationToVerify(){

    this.charityActionService.getBenefactorApplicationById(this.aplicationId).subscribe(
      (response: benefApplicationToCharityActionRead) => {
        this.benefApplication = response;
        this.userLogin=this.benefApplication.benefactor.login;
        console.log(this.benefApplication);

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  akcept(){

  }

  decline(){
    
  }

  public logout(): void {
    localStorage.clear();
    this.router.navigateByUrl('/');
  }
}
