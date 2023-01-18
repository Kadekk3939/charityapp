import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {CharityAction} from "./charity-action";
import { aplicationToCharityAction} from './aplicationToCharityAction';
import {environment} from "../../environments/environment";
import { aplicationToCharityActionRead } from '../charity-action-aplication-list/aplication-to-charity-action-read';

@Injectable({
  providedIn: 'root'
})
export class CharityActionService {
  private apiServerUrl = environment.apiBaseUrl;
  chName:string;
  constructor(private http: HttpClient) { }

  public getCharityActions(): Observable<CharityAction[]>{
    return this.http.get<CharityAction[]>(`${this.apiServerUrl}/action/all`);
  }

  public addCharityAction(charityAction: CharityAction): Observable<CharityAction>{
    return this.http.post<CharityAction>(`${this.apiServerUrl}/action/add`, charityAction);
  }

    public getCharityActionByName(name:string):Observable<CharityAction>{
      return this.http.get<CharityAction>(`${this.apiServerUrl}/action/name/${name}`);
    }

    public postCharityAplication(aplication:aplicationToCharityAction,headers:HttpHeaders):Observable<aplicationToCharityActionRead>{
      return this.http.post<aplicationToCharityActionRead>(`${this.apiServerUrl}/application2charity/add`,aplication, {headers:headers});
    }

    public getListOfCharityAplications(headers:HttpHeaders):Observable<aplicationToCharityActionRead[]>{
      return this.http.get<aplicationToCharityActionRead[]>(`${this.apiServerUrl}/application2charity/all`, {headers:headers});
    }
}
