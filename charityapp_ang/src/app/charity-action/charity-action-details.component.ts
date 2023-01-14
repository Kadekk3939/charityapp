import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { CharityAction } from "./charity-action";
import { CharityActionService } from "./charity-action.service";
import { RouterModule } from '@angular/router';


@Component({
    selector: 'app-charity-action',
    templateUrl: './charity-action-details.component.html',
    styleUrls: ['./charity-action-details.component.css']
  })

  export class CharityActionDetailsComponent implements OnInit {
    public charityAction: CharityAction;
    name:string;
    private sub:any;
    constructor(private charityActionService: CharityActionService,private router:Router,private routeP: ActivatedRoute){}
        
    ngOnInit(): void {
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
    public routeR(){
        this.router.navigateByUrl('/user');
    }
}
