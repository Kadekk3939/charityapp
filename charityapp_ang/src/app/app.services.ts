import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable()
export class AppService {

  authenticated = false;
  private apiServerUr1 = environment.apiBaseUrl;
  constructor(private http: HttpClient) {
  }

  authenticate(credentials: { login: string; password: string; }, callback: () => any) {

        const headers = new HttpHeaders(credentials ? {
            authorization : 'Basic ' + btoa(credentials.login + ':' + credentials.password)
        } : {});

        this.http.get(`${this.apiServerUr1}/user/all`, {headers: headers}).subscribe(response => {
            if (response) {
                this.authenticated = true;
            } else {
                this.authenticated = false;
            }
            return callback && callback();
        });

    }

}
