import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { CharityAction } from "./charity-action";
import { CharityActionService } from "./charity-action.service";
import { RouterModule } from '@angular/router';
import { UserServiceService } from "../user-service.service";
import { AppService } from "../app.service";


@Component({
    selector: 'app-charity-action',
    templateUrl: './charity-action-details.component.html',
    styleUrls: ['./charity-action-details.component.css']
  })

  export class CharityActionDetailsComponent implements OnInit {
    public charityAction: CharityAction|undefined;
    name:string;
    public login:string;
    private sub:any;
    constructor(private charityActionService: CharityActionService,private router:Router,private routeP: ActivatedRoute,private app:AppService){}
        
    ngOnInit(): void {
        this.login = this.app.login;
        this.sub = this.routeP.params.subscribe(params =>
            this.name = params['name'])
            this.charityActionService.getCharityActionByName(this.name).subscribe(
                (response: CharityAction) => {
                  this.charityAction = response;
                  console.log(this.charityAction);
                },
                (error: HttpErrorResponse) => {
                  alert(error.message);
                }
              );
    }

    public logout():void{
      localStorage.clear();
      this.router.navigateByUrl('/');
    }

    public routeR(){
        this.router.navigateByUrl('/user');
    }

    public routeA(){
      this.router.navigate(['/charityAction',this.name,'applay']);
    }

    public routeS(){
      this.router.navigate(['/charityAction',this.name,'support']);
    }

    public isUserBenefactor():boolean{
      if(this.app.user?.role=="Benefactor"){
        return true;
      }
      else{
        return false;
      }
    }
    
    public isUserDonor():boolean{
      if(this.app.user?.role=="Donor"){
        return true;
      }
      else{
        return false;
      }
    }
}
