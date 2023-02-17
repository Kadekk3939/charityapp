import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppService } from '../app.service';
import { CharityAction } from '../charity-action/charity-action';
import { CharityActionRatingService } from './charity-action-rating.service';

@Component({
  selector: 'app-charity-action-rating',
  templateUrl: './rate-charity-action.component.html',
  styleUrls: ['./rate-charity-action.component.css']
})
export class CharityActionRateComponent implements OnInit{
    ngOnInit(): void {
        throw new Error('Method not implemented.');
    }
}