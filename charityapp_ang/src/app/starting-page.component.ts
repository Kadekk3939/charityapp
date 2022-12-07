import { Component ,OnInit, OnDestroy} from "@angular/core";
import { Router } from "@angular/router";
import { AppService } from "./app.services";
import { UserService } from "./user.service";

@Component({
    selector: 'starting-page',
    templateUrl: './html/starting-page.html',
    styleUrls: ['./css/starting-page.css']
})

export class StartingPage implements OnInit,OnDestroy{

    title = 'Starting Page';
    text = '';
    constructor(private userService:UserService,private router: Router,private app: AppService){
        this.text = 'guest';
        console.log(this.app.authenticated);
    };
    /**
     * OnInit is run when page is initilised
     */
    ngOnInit(): void {
        if(this.app.authenticated){
            this.text = 'user';
        }
        else{
            this.text = 'guest';
        }
    }

    /**
     * OnDestroy is run when page is exited
     */
    ngOnDestroy(): void {
        
    }

}