import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { CharityAction } from "./charity-action";
import { CharityActionService } from "./charity-action.service";
import { RouterModule } from '@angular/router';
import { UserServiceService } from "../user-service.service";
import { AppService } from "../app.service";
import { FormControl, FormGroup ,NgForm,ReactiveFormsModule} from '@angular/forms'


@Component({
    selector: 'app-charity-action',
    templateUrl: './charity-action-applay.component.html',
    styleUrls: ['./charity-action-applay.component.css']
  })

  export class CharityActionApplayComponent implements OnInit {
    public charityAction: CharityAction;
    name:string;
    public login:string;
    private sub:any;

    applayForm = new FormGroup({
      actionName: new FormControl(''),
      reason: new FormControl('')
    });

    constructor(private charityActionService: CharityActionService,private router:Router,private routeP: ActivatedRoute,private app:AppService){}
        
    ngOnInit(): void {
        this.login = this.app.login;
        console.log(this.app.user);

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

    public onApplay(applayForm:NgForm){
      applayForm.controls['charityActionName'].setValue(this.name);
      console.log(applayForm);
      this.charityActionService.postCharityAplication(applayForm.value,this.app.headers).subscribe({
        next: (res) => {
            console.log(res);
        },
        error: (err) => {alert(err.message);}
    }
    );
  }
}
