import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppService } from '../app.service';
import { CharityAction } from '../charity-action/charity-action';
import { RateDonotService } from './rate-donor.service';


@Component({
  selector: 'app-charity-action-rating',
  templateUrl: './rate-donor.component.html',
  styleUrls: ['./rate-donor.component.css']
})
export class DonorRateComponent implements OnInit{
    constructor(private routeP: ActivatedRoute,private router:Router,private ratingService:RateDonotService,
        private app:AppService){}
        public err:boolean;
        private sub: any;
        public name:string;
        public action:string;
    ngOnInit(): void {
        this.sub = this.routeP.params.subscribe(params =>{
            this.name = params["login"];
            this.action=params["action"];
        })

    }

    onRate(rateForm:NgForm){
        rateForm.controls['charityActionName'].setValue(this.action);
        rateForm.controls['donorLogin'].setValue(this.name);
        this.ratingService.postDonorRating(rateForm.value,this.app.headers).subscribe(
            (response:string)=>{
                console.log(response);
                this.router.navigateByUrl('/rateDonorsList');
            },
            (error:HttpErrorResponse)=>{
                console.log(error)
            }
        )
        console.log(rateForm);
    }

    public logout(): void {
        localStorage.clear();
        this.router.navigateByUrl('/');
      }
}