import { Component, OnInit } from '@angular/core';
import { CharityAction } from "../charity-action/charity-action";
import { HttpErrorResponse } from "@angular/common/http";
import { CharityActionService } from "../charity-action/charity-action.service";
import { NgForm } from "@angular/forms";

@Component({
  selector: 'app-benefactor-browse-actions',
  templateUrl: './benefactor-browse-actions.component.html',
  styleUrls: ['./benefactor-browse-actions.component.css']
})
export class BenefactorBrowseActionsComponent implements OnInit {
  public charityActions: CharityAction[];

  constructor(private charityActionService: CharityActionService) { }


  ngOnInit() {
    this.getCharityActions();
  }

  public getCharityActions(): void {
    this.charityActionService.getCharityActions().subscribe(
      (response: CharityAction[]) => {
        this.charityActions = response;
        console.log(this.charityActions);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onOpenModal(mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'apply') {
      //this.editEmployee = employee;
      button.setAttribute('data-target', '#applyModal');
    }
    // @ts-ignore
    container.appendChild(button);
    button.click();
  }

  public onApply(applyForm: NgForm): void {

  }

}
