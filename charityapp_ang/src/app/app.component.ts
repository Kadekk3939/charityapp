import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from './user';
import { UserService } from './user.service';

@Component({
  selector: 'app-root',
  templateUrl: './html/app.component.html',
  styleUrls: ['./css/app.component.css']
})
export class AppComponent implements OnInit{
  title = 'charityapp_ang';
  public users: User[] | undefined;

  constructor(private userService:UserService){}

  ngOnInit(): void {
      this.getUsers();
  }

  public getUsers():void{
    this.userService.getUsers().subscribe(
      (response:User[])=>{
        this.users = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
