import { Component ,OnInit, OnDestroy} from "@angular/core";
import {Location} from '@angular/common';

@Component({
    selector: 'login-page',
    templateUrl: './html/login-page.html',
    styleUrls: ['./css/login-page.css']
})

export class LoginPage implements OnInit,OnDestroy{

    title = 'Login Page';

    constructor(private _location: Location){}
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

}
