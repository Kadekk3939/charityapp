import {Component, OnInit} from '@angular/core';
import {CharityAction} from "../charity-action";
import {CharityActionService} from "../charity-action.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-charity-action',
  templateUrl: './charity-action.component.html',
  styleUrls: ['./charity-action.component.css']
})
export class CharityActionComponent implements OnInit {
  public charityActions: CharityAction[] | undefined;

  constructor(private charityActionService: CharityActionService){}

  ngOnInit() {
    this.getCharityActions();
  }

  public getCharityActions(): void {
    this.charityActionService.getCharityActions().subscribe(
      (response: CharityAction[]) => {
        this.charityActions = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
