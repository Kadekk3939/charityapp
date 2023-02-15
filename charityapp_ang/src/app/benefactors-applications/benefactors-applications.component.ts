import { Component} from '@angular/core';
import {Router} from "@angular/router";
import {CharityActionService} from "../charity-action/charity-action.service";
import {AppService} from "../app.service";

@Component({
  selector: 'app-benefactors-applications',
  templateUrl: './benefactors-applications.component.html',
  styleUrls: ['./benefactors-applications.component.css']
})
export class BenefactorsApplicationsComponent {
  constructor(private router: Router, private charityActionService: CharityActionService, private app: AppService) { }

  public logout(): void {
    localStorage.clear();
    this.router.navigateByUrl('/');
  }
}
