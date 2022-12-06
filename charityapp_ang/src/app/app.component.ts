import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from './user';
import { UserRole } from './user-role';
import { UserService } from './user.service';

@Component({
  selector: 'app-root',
  templateUrl: './html/app.component.html',
  styleUrls: ['./css/app.component.css']
})
export class AppComponent implements OnInit{
  title = 'charityapp_ang';

  constructor(private userService:UserService){}

  ngOnInit(): void {
      
  }

}
