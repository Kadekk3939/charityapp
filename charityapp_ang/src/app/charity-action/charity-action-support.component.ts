import { HttpErrorResponse } from "@angular/common/http";
import { LOCALE_ID,Component, OnInit, Inject } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { CharityAction } from "./charity-action";
import { CharityActionService } from "./charity-action.service";
import { RouterModule } from '@angular/router';
import { UserServiceService } from "../user-service.service";
import { AppService } from "../app.service";
import { NgForm } from "@angular/forms";
import { CommonModule, formatNumber } from "@angular/common";


@Component({
  selector: 'app-charity-action',
  templateUrl: './charity-action-support.component.html',
  styleUrls: ['./charity-action-support.component.css']
})

export class CharityActionSupportComponent implements OnInit {

  public err: boolean;
  name: string;
  public login: string;
  private sub: any;
  formattedAmount: string|number | null;
  currentAmount: string | number | null;
  amount: any;

  constructor(@Inject(LOCALE_ID) public locale: string,private charityActionService: CharityActionService,
   private router: Router, private routeP: ActivatedRoute, private app: AppService) { }

  ngOnInit(): void {
    this.app.refresh();
    this.login = this.app.login;
    console.log(this.app.user);
    this.sub = this.routeP.params.subscribe(params =>
      this.name = params['name'])
  }

  public onDonation(donationForm:NgForm){
    donationForm.controls['charityActionName'].setValue(this.name);

    this.charityActionService.postCharityDonation(donationForm.value, this.app.headers).subscribe({
      next: (res) => {
        console.log(res);
        this.router.navigateByUrl('/charityActionAplicationList');
      },
      error: (err) => { 
        if(donationForm.value.amount==0){
          this.err = true;
        }
       }
    }
    );
  }

  public logout(): void {
    localStorage.clear();
    this.router.navigateByUrl('/');
  }
}
