import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from 'src/environments/environment';
import { CharityAction } from '../charity-action/charity-action';


@Injectable({
    providedIn: 'root'
  })
  export class CharityActionRatingService {
    private apiServerUrl = environment.apiBaseUrl;
    chName: string;
    constructor(private http: HttpClient) { }

    public getActionsToRate( headers: HttpHeaders): Observable<CharityAction[]> {
        return this.http.get<CharityAction[]>(`${this.apiServerUrl}/rating/all`, { headers: headers });
      }

  }