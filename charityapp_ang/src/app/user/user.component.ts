import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { AppService } from '../app.service';
import { User } from '../user';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  headers:HttpHeaders;
  login:string = '';
  public user:User;

  constructor (private app: AppService,private http: HttpClient,private userService:UserServiceService,private router:Router){
    if(this.login == ''){
      this.app.refresh();
    }
  }
  
  ngOnInit(): void {

    
    this.headers = this.app.headers;
    this.login = this.app.login;
    console.log(localStorage.getItem('login'));
    
    this.getUser();

  }

  public logout():void{
    localStorage.clear();
    this.router.navigateByUrl('/');
  }

  public getUser():void{
    this.userService.getUserByLogin(this.login,this.headers).subscribe(
      (response: User) => {
        this.user = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
