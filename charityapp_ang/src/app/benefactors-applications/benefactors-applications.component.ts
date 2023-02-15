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
  selector: 'app-benefactors-applications',
  templateUrl: './benefactors-applications.component.html',
  styleUrls: ['./benefactors-applications.component.css']
})
export class BenefactorsApplicationsComponent {

}
