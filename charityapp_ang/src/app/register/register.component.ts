import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  constructor(private userService: UserServiceService,private router: Router){};

  chosenRole: string;
  roles: string[] = ['Donor', 'Benefactor'];

  public onRegisterUser(registerForm:NgForm){
    console.log(registerForm.value);
    this.userService.addUser(registerForm.value).subscribe({
      next: (res) => {
          console.log(res);
          this.router.navigateByUrl('');
      },
      error: (err) => {alert(err.message);}
  })
  }
}
