import { Component ,OnInit, OnDestroy} from "@angular/core";

@Component({
    selector: 'starting-page',
    templateUrl: './html/starting-page.html',
    styleUrls: ['./css/starting-page.css']
})

export class StartingPage implements OnInit,OnDestroy{

    title = 'Starting Page';

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