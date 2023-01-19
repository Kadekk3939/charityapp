import { Component, OnInit } from '@angular/core';
import { CharityAction } from "./charity-action";
import { CharityActionService } from "./charity-action.service";
import { HttpErrorResponse } from "@angular/common/http";
//import {Router} from "@angular/router";
import { NgForm } from "@angular/forms";
import { Router } from '@angular/router';
import { User } from '../user';
import { AppService } from '../app.service';

@Component({
  selector: 'app-charity-action',
  templateUrl: './charity-action.component.html',
  styleUrls: ['./charity-action.component.css']
})
export class CharityActionComponent implements OnInit {
  public charityActions: CharityAction[];
  private user: User;

  constructor(private charityActionService: CharityActionService, private router: Router, private app: AppService) { }

  ngOnInit() {
    this.app.refresh();
    this.getCharityActions();
    this.user = this.app.user;
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

  public onAddAction(addForm: NgForm): void {
    // @ts-ignore
    document.getElementById('add-action-form').click();
    this.charityActionService.addCharityAction(addForm.value).subscribe(
      (response: CharityAction) => {
        console.log(response);
        this.getCharityActions();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onOpenModal(mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addActionModal');
    }
    // if (mode === 'edit') {
    //   this.editEmployee = employee;
    //   button.setAttribute('data-target', '#updateEmployeeModal');
    // }
    // if (mode === 'delete') {
    //   this.deleteEmployee = employee;
    //   button.setAttribute('data-target', '#deleteEmployeeModal');
    // }
    // @ts-ignore
    container.appendChild(button);
    button.click();
  }

  public show(actionName: string) {
    this.charityActionService.chName = actionName;
    console.log(actionName);
    this.router.navigate(['/charityAction', actionName]);
  }

  public logout(): void {
    console.log(this.user);
    localStorage.clear();
    this.router.navigateByUrl('/');
  }

  public userIsWorker(): boolean {
    if (this.user.role == "Worker") {
      return true;
    }
    return false;
  }

}
