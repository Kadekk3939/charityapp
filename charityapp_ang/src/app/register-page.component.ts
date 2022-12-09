import { HttpErrorResponse } from "@angular/common/http";
import { Component ,OnInit, OnDestroy} from "@angular/core";
import { NgForm } from "@angular/forms";
import {Router} from '@angular/router'
import { User } from "./user";
import { UserService } from "./user.service";

@Component({
    selector: 'register-page',
    templateUrl: './html/register-page.html',
    styleUrls: ['./css/register-page.css']
})

export class RegisterPage implements OnInit,OnDestroy{

    title = 'Register Page';

    public user: User | undefined;
    constructor(private userService:UserService,private router: Router){}
    /**
     * OnInit is run when page is initilised
     */
    ngOnInit(): void {
        
    }

    /**
     * OnDestroy is run when page is exited
     */
    ngOnDestroy(): void {
        
    }

    public onRegisterUser(registerForm:NgForm):void{
        this.userService.addUser(registerForm.value).subscribe({
            next: (res) => {
                console.log(res);
                this.router.navigateByUrl('');
            },
            error: (err) => {alert(err.message);}
        })
    }

}