import { Component ,OnInit, OnDestroy} from "@angular/core";

@Component({
    selector: 'user-page',
    templateUrl: './html/user-page.html',
    styleUrls: ['./css/user-page.css']
})

export class UserPage implements OnInit,OnDestroy{

    title = 'User Page';

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