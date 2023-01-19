import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-starting-page',
  templateUrl: './starting-page.component.html',
  styleUrls: ['./starting-page.component.css']
})
export class StartingPageComponent implements OnInit {

  constructor(private userService: UserServiceService, private router: Router) { }

  ngOnInit(): void {
    if (localStorage.length == 2) {
      this.router.navigateByUrl('/user');
    }

  }

}
