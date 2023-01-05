import { Component } from '@angular/core';
import {User} from "../user";


@Component({
  selector: 'app-benefactor-profile',
  templateUrl: './benefactor-profile.component.html',
  styleUrls: ['./benefactor-profile.component.css']
})
export class BenefactorProfileComponent {
  public user: User;

}
