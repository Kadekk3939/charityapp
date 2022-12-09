import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from '../app.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  credentials = {login: '', password: ''};
  authenticatied:boolean = false
  constructor(private app: AppService, private http: HttpClient, private router: Router) {
  }

  login(){
    this.app.authenticate(this.credentials, () => {  
      this.router.navigateByUrl('/user');
  });
  return false;
  }
}
