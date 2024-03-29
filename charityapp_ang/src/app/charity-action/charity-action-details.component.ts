import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { CharityAction } from "./charity-action";
import { CharityActionService } from "./charity-action.service";
import { RouterModule } from '@angular/router';
import { UserServiceService } from "../user-service.service";
import { AppService } from "../app.service";
import { aplicationToCharityActionRead } from "../charity-action-aplication-list/aplication-to-charity-action-read";
import { Donation } from "../donors-donation-history/donation";


@Component({
  selector: 'app-charity-action',
  templateUrl: './charity-action-details.component.html',
  styleUrls: ['./charity-action-details.component.css']
})

export class CharityActionDetailsComponent implements OnInit {
  public charityAction: CharityAction | undefined;
  name: string;
  public login: string;
  public status:boolean|null;
  public reason: string;
  public donations:Donation[];
  public photos:string[];
  private sub: any;
  public path:string;
  filenames: string[] = [];
  constructor(private charityActionService: CharityActionService, private router: Router, private routeP: ActivatedRoute, private app: AppService) { }

  ngOnInit(): void {
    this.status = null;
    this.app.refresh();
    this.login = this.app.login;
    this.path='/img/';
    this.sub = this.routeP.params.subscribe(params =>
      this.name = params["name"])
      console.log(this.name);
    this.charityActionService.getCharityActionByName(this.name).subscribe(
      (response: CharityAction) => {
        this.charityAction = response;
        this.donations=this.charityAction.topDonors;
        console.log(this.charityAction);
        this.photos = this.charityAction.images;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
    this.charityActionService.getUserApplicationStatus(this.name,this.login).subscribe(
      (response:string)=>{
        if(response==null){
          this.status=true;
        }
        else if(response=="ACCEPTED"){
          this.status=false;
          this.reason='Accepted'

        }
        else if(response=="UNCHECKED"){
          this.status=false;
          this.reason='Unchecked'

        }
        else if(response=="DECLINED"){
          this.status=true;
        }
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public logout(): void {
    localStorage.clear();
    this.router.navigateByUrl('/');
  }

  public routeR() {
    this.router.navigateByUrl('/user');
  }

  public routeA() {
    this.router.navigate(['/charityAction', this.name, 'applay']);
  }

  public routeS() {
    this.router.navigate(['/charityAction', this.name, 'support']);
  }

  public isUserBenefactor(): boolean {
    if (this.app.user?.role == "Benefactor") {
      return true;
    }
    else {
      return false;
    }
  }

  public isUserWorker(): boolean {
    if (this.app.user?.role == "Worker") {
      return true;
    }
    else {
      return false;
    }
  }

  public endCharityAction() {
    this.charityActionService.endCharityAction(this.name).subscribe(
      (response: Boolean) => {
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
    this.router.navigateByUrl('/charityAction');
  }

  public isUserDonor(): boolean {
    if (this.app.user?.role == "Donor") {
      return true;
    }
    else {
      return false;
    }
  }

  public addImage():void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    button.setAttribute('data-target', '#addImageModal');
    // @ts-ignore
    container.appendChild(button);
    button.click();
  }

  public onUpload(files: File[]): void {
    const formData = new FormData();
    for (const file of files) {
      if(file && file['type'].split('/')[0] === 'image')
        formData.append('files', file, file.name);
      else
        alert("Please provide an image file");
    }
    this.charityActionService.uploadImage(this.name,formData).subscribe(
      event => {
        console.log(event);
        //this.raport(event);

      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }
  public finish(){
    this.router.navigateByUrl('/charityAction/'+this.name);

  }
}
