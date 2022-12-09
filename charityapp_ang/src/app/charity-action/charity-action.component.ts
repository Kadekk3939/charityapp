import {Component, OnInit} from '@angular/core';
import {CharityAction} from "./charity-action";
import {CharityActionService} from "../charity-action.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";

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

  // public onAddAction(addForm: NgForm): void {
  //   // @ts-ignore
  //   document.getElementById('add-action-form').click();
  //   this.charityActionService.addCharityAction(addForm.value).subscribe(
  //     (response: CharityAction) => {
  //       console.log(response);
  //       this.getCharityActions();
  //       addForm.reset();
  //     },
  //     (error: HttpErrorResponse) => {
  //       alert(error.message);
  //       addForm.reset();
  //     }
  //   );
  // }

  // public onOpenModal(chAction: CharityAction, mode: string): void {
  //   const container = document.getElementById('main-container');
  //   const button = document.createElement('button');
  //   button.type = 'button';
  //   button.style.display = 'none';
  //   button.setAttribute('data-toggle', 'modal');
  //   if (mode === 'add') {
  //     button.setAttribute('data-target', '#addActionModal');
  //   }
  //   // if (mode === 'edit') {
  //   //   this.editEmployee = employee;
  //   //   button.setAttribute('data-target', '#updateEmployeeModal');
  //   // }
  //   // if (mode === 'delete') {
  //   //   this.deleteEmployee = employee;
  //   //   button.setAttribute('data-target', '#deleteEmployeeModal');
  //   // }
  //   container.appendChild(button);
  //   button.click();
  // }



}
