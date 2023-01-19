import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from '../app.service';
import { MatRadioModule } from '@angular/material/radio';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  credentials = { login: '', password: '' };
  authenticatied: boolean = false


  constructor(private app: AppService, private http: HttpClient, private router: Router) {
  }

  login() {
    localStorage.clear()
    this.app.authenticate(this.credentials, () => {
      this.router.navigateByUrl('/user');
    });
    return false;
  }
}
