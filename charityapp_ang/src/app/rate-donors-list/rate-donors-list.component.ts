import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {CharityActionService} from "../charity-action/charity-action.service";
import {AppService} from "../app.service";
import { Donation } from '../donors-donation-history/donation';
import { RateDonotService } from './rate-donor.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { RatingDonation } from './ratingDonation';

@Component({
  selector: 'app-rate-donors-list',
  templateUrl: './rate-donors-list.component.html',
  styleUrls: ['./rate-donors-list.component.css']
})
export class RateDonorsListComponent implements OnInit{
  constructor(private router: Router, private charityActionService: CharityActionService, private app: AppService,
    private ratingService:RateDonotService) { }
  public donations:RatingDonation[];
  noDonation:boolean;
  ngOnInit(): void {
    this.app.refresh();
    this.ratingService.getDonorsToRate(this.app.headers).subscribe(
      (response:RatingDonation[])=>{
      this.donations = response;
      console.log(response);
      },
      (error:HttpErrorResponse)=>{
        console.log(error);
      }
    );
  }
  public logout(): void {
    localStorage.clear();
    this.router.navigateByUrl('/');
  }

  rate(donation:RatingDonation){
    this.router.navigate(['/rateDonorsList', donation.donorLogin,donation.charityActionName]);
  }

  noDonationCheck():boolean{
    return this.noDonation;
  }
}
