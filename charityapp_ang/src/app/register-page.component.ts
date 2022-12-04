import { Component ,OnInit, OnDestroy} from "@angular/core";

@Component({
    selector: 'register-page',
    templateUrl: './html/register-page.html',
    styleUrls: ['./css/register-page.css']
})

export class RegisterPage implements OnInit,OnDestroy{

    title = 'Register Page';

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

}