import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from '../app.service';
import { Donation } from './donation';
import { DonationService } from './donation.service';

@Component({
  selector: 'app-donors-donation-history',
  templateUrl: './donors-donation-history.component.html',
  styleUrls: ['./donors-donation-history.component.css']
})
export class DonorsDonationHistoryComponent implements OnInit{
  
  constructor(private router: Router, private app: AppService,private donationService:DonationService) { }

  public donations:Donation[];

  ngOnInit(): void {
    this.app.refresh();
    this.donationService.getDonations(this.app.headers).subscribe(
      (response:Donation[])=>{
        this.donations = response;
        console.log(this.donations);
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
