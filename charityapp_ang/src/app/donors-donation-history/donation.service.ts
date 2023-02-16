import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from 'src/environments/environment';
import { Donation } from './donation';


@Injectable({
    providedIn: 'root'
  })
  export class DonationService {
    private apiServerUrl = environment.apiBaseUrl;
    chName: string;
    constructor(private http: HttpClient) { }

    public getDonations( headers: HttpHeaders): Observable<Donation[]> {
        return this.http.get<Donation[]>(`${this.apiServerUrl}/donation/all`, { headers: headers });
      }

  }