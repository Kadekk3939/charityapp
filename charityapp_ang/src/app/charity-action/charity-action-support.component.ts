import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { CharityAction } from "./charity-action";
import { CharityActionService } from "./charity-action.service";
import { RouterModule } from '@angular/router';
import { UserServiceService } from "../user-service.service";
import { AppService } from "../app.service";
import { NgForm } from "@angular/forms";


@Component({
  selector: 'app-charity-action',
  templateUrl: './charity-action-support.component.html',
  styleUrls: ['./charity-action-support.component.css']
})

export class CharityActionSupportComponent implements OnInit {
  public charityAction: CharityAction;
  name: string;
  public login: string;
  private sub: any;
  constructor(private charityActionService: CharityActionService, private router: Router, private routeP: ActivatedRoute, private app: AppService) { }

  ngOnInit(): void {
    this.app.refresh();
    this.login = this.app.login;
    console.log(this.app.user);
    this.sub = this.routeP.params.subscribe(params =>
      this.name = params['name'])
  }

  public logout(): void {
    localStorage.clear();
    this.router.navigateByUrl('/');
  }
}
