import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from '../app.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  credentials = {login: '', password: ''};

  constructor(private app: AppService, private http: HttpClient, private router: Router) {
  }

  login(){
    this.app.authenticate(this.credentials, () => {
      this.router.navigateByUrl('/');
  });
  return false;
  }
}
