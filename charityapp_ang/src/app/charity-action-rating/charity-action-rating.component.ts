import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppService } from '../app.service';
import { CharityAction } from '../charity-action/charity-action';
import { CharityActionRatingService } from './charity-action-rating.service';

@Component({
  selector: 'app-charity-action-rating',
  templateUrl: './charity-action-rating.component.html',
  styleUrls: ['./charity-action-rating.component.css']
})
export class CharityActionRatingComponent implements OnInit{

constructor(private app:AppService,private ratingService:CharityActionRatingService,private router:Router){}

  charityActions:CharityAction[];
  noActions:boolean;

  ngOnInit(): void {
    this.app.refresh();
    this.ratingService.getActionsToRate(this.app.headers).subscribe(
      (response:CharityAction[])=>{
        this.charityActions=response;
        if(this.charityActions.length==0){
          this.noActions=true;
        }
        else{
          this.noActions = false;
        }
      },
      (error:HttpErrorResponse)=>{
        console.log(error);
      }
    )
  }

  rate(actionName:string){
    this.router.navigate(['/actionRating', actionName]);
  }

  public logout(): void {
    localStorage.clear();
    this.router.navigateByUrl('/');
  }

  public noActionsCheck():boolean{
    console.log(this.noActions);
    return this.noActions;
  }
}
