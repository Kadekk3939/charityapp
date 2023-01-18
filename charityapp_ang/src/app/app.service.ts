import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { UserServiceService } from './user-service.service';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';
import { User } from './user';

@Injectable()
export class AppService {

  authenticated = false;
  public headers:HttpHeaders;
  public login:string;
  public user:User;
  credentials = {login: '', password: ''}
  constructor(private http: HttpClient,private router : Router,private userService:UserServiceService) {
  }

  public getAuthenticated():boolean{
    return this.authenticated;
  }
  
  public refresh():void{
    

    if(localStorage.length==2){
        if(this.headers == undefined){
            this.credentials.login = localStorage.getItem('login')!;
            this.credentials.password = localStorage.getItem('password')!;
            this.login = localStorage.getItem('login')!
            this.authenticate(this.credentials, () => {  
                console.log('Refresh');
            
            });

            this.userService.getUserByLogin(this.login).subscribe(
                (response: User) => {
                  this.user = response;
                  console.log(this.user);
                },
                (error: HttpErrorResponse) => {
                  alert(error.message);
                }
              );

        }
    }
    else{
        console.log('Und');
        this.router.navigateByUrl('/');
    }
    
  }

  authenticate(credentials: { login: string; password: any}, callback: { (): void; (): any; }) {
        this.login = credentials.login;
        this.headers = new HttpHeaders(credentials ? {
            authorization : 'Basic ' + btoa(credentials.login + ':' + credentials.password)
        } : {});
        localStorage.clear()
        localStorage.setItem('login',credentials.login);
        localStorage.setItem('password',credentials.password);
        this.http.get(`${environment.apiBaseUrl}/user/find/login/${credentials.login}`, {headers: this.headers}).subscribe(response => {
            if (response) {
                this.authenticated = true;
            } else {
                this.authenticated = false;
            }
            return callback && callback();
        });

    }


    

}
