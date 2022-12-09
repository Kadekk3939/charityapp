import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserServiceService } from './user-service.service';
import { environment } from 'src/environments/environment';

@Injectable()
export class AppService {

  authenticated = false;
  public headers:HttpHeaders;
  public login:string;
  constructor(private http: HttpClient) {
  }

  public getAuthenticated():boolean{
    return this.authenticated;
  }
  

  authenticate(credentials: { login: string; password: any}, callback: { (): void; (): any; }) {
        this.login = credentials.login;
        this.headers = new HttpHeaders(credentials ? {
            authorization : 'Basic ' + btoa(credentials.login + ':' + credentials.password)
        } : {});

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
