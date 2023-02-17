import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { aplicationToCharityActionRead } from '../charity-action-aplication-list/aplication-to-charity-action-read';
import {
  benefApplicationToCharityActionRead
} from "../benefactors-applications/benef-application-to-charity-action-read";
import { Donation } from '../donors-donation-history/donation';
import { DonorRating } from './donor-rating';
import { RatingDonation } from './ratingDonation';

@Injectable({
  providedIn: 'root'
})
export class RateDonotService {
    constructor(private http: HttpClient) { }
    private apiServerUrl = environment.apiBaseUrl;

    public getDonorsToRate( headers: HttpHeaders): Observable<RatingDonation[]> {
        return this.http.get<RatingDonation[]>(`${this.apiServerUrl}/donor/rating/all`, { headers: headers });
      }
      public postDonorRating(rating:DonorRating,headers: HttpHeaders){
        return this.http.post(`${this.apiServerUrl}/donor/rating/add`,rating, { headers: headers,responseType: 'text'})
      }

}