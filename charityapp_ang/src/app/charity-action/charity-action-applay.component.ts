import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { CharityAction } from "./charity-action";
import { CharityActionService } from "./charity-action.service";
import { RouterModule } from '@angular/router';
import { UserServiceService } from "../user-service.service";
import { AppService } from "../app.service";
import { FormControl, FormGroup, NgForm, ReactiveFormsModule } from '@angular/forms'


@Component({
  selector: 'app-charity-action',
  templateUrl: './charity-action-applay.component.html',
  styleUrls: ['./charity-action-applay.component.css']
})

export class CharityActionApplayComponent implements OnInit {
  public charityAction: CharityAction;
  name: string;
  public err:boolean;
  public login: string;
  private sub: any;
  private action:number;

  applayForm = new FormGroup({
    actionName: new FormControl(''),
    reason: new FormControl('')
  });

  constructor(private charityActionService: CharityActionService, private router: Router,
     private routeP: ActivatedRoute, private app: AppService) { 
      this.err=false;
      
     }

  ngOnInit(): void {
    this.app.refresh();
    this.login = this.app.login;
    console.log(this.app.user);

    this.sub = this.routeP.params.subscribe(params =>
      this.name = params['name'])
    this.charityActionService.getCharityActionByName(this.name).subscribe(
      (response: CharityAction) => {
        this.charityAction = response;
        console.log(this.charityAction);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public logout(): void {
    localStorage.clear();
    this.router.navigateByUrl('/');
  }

  public onApplay(applayForm: NgForm) {
    applayForm.controls['charityActionName'].setValue(this.name);
    console.log(applayForm);
    this.charityActionService.postCharityAplication(applayForm.value, this.app.headers).subscribe({
      next: (res) => {
        this.action=res;
        console.log(res);
        this.router.navigateByUrl('/charityActionAplicationList');
      },
      error: (err) => { 
        if(applayForm.value.reason==""){
          this.err = true;
        }
       }
    }
    );
  }

  public onOpenModal(): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    button.setAttribute('data-target', '#addDocumentModal');
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

  public onAddDocuments(addForm: NgForm): void {
    // @ts-ignore
    document.getElementById('add-action-form').click();
    this.charityActionService.addCharityAction(addForm.value).subscribe(
      (response: CharityAction) => {
        console.log(response);
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }
}
