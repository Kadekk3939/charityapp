import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppService } from '../app.service';
import { CharityAction } from '../charity-action/charity-action';
import { CharityActionRating } from './charity-action-rating';
import { CharityActionRatingService } from './charity-action-rating.service';

@Component({
  selector: 'app-charity-action-rating',
  templateUrl: './rate-charity-action.component.html',
  styleUrls: ['./rate-charity-action.component.css']
})
export class CharityActionRateComponent implements OnInit{
    constructor(private routeP: ActivatedRoute,private router:Router,private ratingService:CharityActionRatingService,
        private app:AppService){}
    public name:string;
    private sub: any;
    public err:boolean;
    public rate:CharityActionRating;
    ngOnInit(): void {
        this.sub = this.routeP.params.subscribe(params =>
            this.name = params["name"])
        this.err=false;
    }

    onRate(rateForm:NgForm){
        rateForm.controls['actionName'].setValue(this.name);
        console.log(rateForm.value);
        this.ratingService.postActionRating(rateForm.value,this.app.headers).subscribe(
            (response:string)=>{
                console.log(rateForm);
                this.router.navigateByUrl('/actionRating');
            },
            (error:HttpErrorResponse)=>{
                console.log(error);
            }
        )

    }

    public logout(): void {
        localStorage.clear();
        this.router.navigateByUrl('/');
      }
}
