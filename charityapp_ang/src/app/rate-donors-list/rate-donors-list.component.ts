import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {CharityActionService} from "../charity-action/charity-action.service";
import {AppService} from "../app.service";

@Component({
  selector: 'app-rate-donors-list',
  templateUrl: './rate-donors-list.component.html',
  styleUrls: ['./rate-donors-list.component.css']
})
export class RateDonorsListComponent {
  constructor(private router: Router, private charityActionService: CharityActionService, private app: AppService) { }

  public logout(): void {
    localStorage.clear();
    this.router.navigateByUrl('/');
  }
}
