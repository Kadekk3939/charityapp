import { Component ,OnInit, OnDestroy} from "@angular/core";
import {Location} from '@angular/common';
import { NgForm } from "@angular/forms";
import { UserService } from "./user.service";
import { Router } from "@angular/router";
import { AppService } from "./app.services";

@Component({
    selector: 'login-page',
    templateUrl: './html/login-page.html',
    styleUrls: ['./css/login-page.css']
})

export class LoginPage implements OnInit,OnDestroy{

    title = 'Login Page';
    credentials = {login: '', password: ''};

    constructor(private _location: Location,private userService:UserService,private router: Router,private app: AppService){}
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
    back(){
        this._location.back();
    }

    login() {
    this.app.authenticate(this.credentials, () => {
        console.log(this.credentials.login);
        this.router.navigateByUrl('/');
    });
    return false
}

}
