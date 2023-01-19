import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from '../app.service';
import { aplicationToCharityActionRead } from './aplication-to-charity-action-read';
import { CharityActionService } from '../charity-action/charity-action.service';

@Component({
  selector: 'app-charity-action-aplication-list',
  templateUrl: './charity-action-aplication-list.component.html',
  styleUrls: ['./charity-action-aplication-list.component.css']
})
export class CharityActionAplicationListComponent implements OnInit {

  constructor(private router: Router, private charityActionService: CharityActionService, private app: AppService) { }
  charityAplications: aplicationToCharityActionRead[];

  ngOnInit(): void {
    this.app.refresh();
    this.getListOfAplications();
  }

  getListOfAplications() {
    this.charityActionService.getListOfCharityAplications(this.app.headers).subscribe(
      (response: aplicationToCharityActionRead[]) => {
        this.charityAplications = response;
        console.log(this.charityAplications);
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
}
