import { Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {CharityActionService} from "../charity-action/charity-action.service";
import {AppService} from "../app.service";
import {aplicationToCharityActionRead} from "../charity-action-aplication-list/aplication-to-charity-action-read";
import {benefApplicationToCharityActionRead} from "./benef-application-to-charity-action-read";
import {HttpErrorResponse, HttpEvent, HttpEventType} from "@angular/common/http";
import { BenefactorDocument } from '../charity-action/document';
import {saveAs} from 'file-saver'

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
  public files:BenefactorDocument[];
  public noAvaibleActions:boolean;

  ngOnInit(): void {
    this.noAvaibleActions=false;
    this.app.refresh();
    this.getBenefactorApplication();
    
  }

  getBenefactorApplication() {
    this.charityActionService.getBenefactorApplication(this.app.headers).subscribe(
      (response: number) => {
        this.aplicationId = response;
        if(this.aplicationId==null){
          this.noAvaibleActions=true;
          this.noAplicationsToVerify();
        }
        else{
        console.log(this.aplicationId);
        this.getBenefactoAplicationToVerify();
        }

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
        this.files = this.benefApplication.documents

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  noAplicationsToVerify():boolean{
    return this.noAvaibleActions;
  }

  akcept(){
    this.charityActionService.acceptedAplicationToAction(this.aplicationId)
        this.router.navigateByUrl('/user');

      
  }

  decline(){
    this.charityActionService.rejectAplicationToAction(this.aplicationId)
        this.router.navigateByUrl('/user');

      
  }

  onDownload(file:BenefactorDocument){
    console.log(file);
    this.charityActionService.download(file).subscribe(
      event=>{
        this.download(event,file.fileName);
      },
      (error: HttpErrorResponse)=>{
        console.log(error);
      }
    );

  }

  private download(httpEvent: HttpEvent<string[] | Blob>,file:string): void {
    switch(httpEvent.type) {
      case HttpEventType.Response:
        if (httpEvent.body instanceof Array) {} 
        else {
          saveAs(new File([httpEvent.body!], file!, 
                  {type: `${httpEvent.headers.get('Content-Type')};charset=utf-8`}));
          // saveAs(new Blob([httpEvent.body!], 
          //   { type: `${httpEvent.headers.get('Content-Type')};charset=utf-8`}),
          //    httpEvent.headers.get('File-Name'));
        }
    }
  }

  public logout(): void {
    localStorage.clear();
    this.router.navigateByUrl('/');
  }
}
