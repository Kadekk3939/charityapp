import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { CharityAction } from "./charity-action";
import { aplicationToCharityAction } from './aplication-to-charity-action';
import { environment } from "../../environments/environment";
import { aplicationToCharityActionRead } from '../charity-action-aplication-list/aplication-to-charity-action-read';
import { donationToCharityAction } from './donation-to-charity-action';
import {
  benefApplicationToCharityActionRead
} from "../benefactors-applications/benef-application-to-charity-action-read";
import { BenefactorDocument } from './document';

@Injectable({
  providedIn: 'root'
})
export class CharityActionService {
  private apiServerUrl = environment.apiBaseUrl;
  chName: string;
  constructor(private http: HttpClient) { }

  public getCharityActions(): Observable<CharityAction[]> {
    return this.http.get<CharityAction[]>(`${this.apiServerUrl}/action/all`);
  }

  public addCharityAction(charityAction: CharityAction): Observable<CharityAction> {
    return this.http.post<CharityAction>(`${this.apiServerUrl}/action/add`, charityAction);
  }

  public endCharityAction(name: string): Observable<Boolean> {
    return this.http.patch<Boolean>(`${this.apiServerUrl}/action/close/${name}`, name);
  }

  public getCharityActionByName(name: string): Observable<CharityAction> {
    return this.http.get<CharityAction>(`${this.apiServerUrl}/action/name/${name}`);
  }

  public postCharityAplication(aplication: aplicationToCharityAction, headers: HttpHeaders): Observable<number> {
    return this.http.post<number>(`${this.apiServerUrl}/application2charity/add`, aplication, { headers: headers });
  }

  public postCharityDonation(donation: donationToCharityAction, headers: HttpHeaders): Observable<donationToCharityAction> {
    return this.http.post<donationToCharityAction>(`${this.apiServerUrl}/donation/add`, donation, { headers: headers });
  }

  public getListOfCharityAplications(headers: HttpHeaders): Observable<aplicationToCharityActionRead[]> {
    return this.http.get<aplicationToCharityActionRead[]>(`${this.apiServerUrl}/application2charity/all`, { headers: headers });
  }

  public getBenefactorApplication(headers: HttpHeaders): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/application2charity/random`, { headers: headers });
  }

  public getBenefactorApplicationById(aplicationId:number): Observable<benefApplicationToCharityActionRead> {
    return this.http.get<benefApplicationToCharityActionRead>(`${this.apiServerUrl}/application2charity/find/${aplicationId}`);
  }

  public getUserApplicationStatus(action:string,benefactor:string): Observable<string> {
    return this.http.get<string>(`${this.apiServerUrl}/application2charity/${action}/${benefactor}`);
  }

  upload(applicationId:number,formData: FormData): Observable<HttpEvent<string[]>> {
    return this.http.post<string[]>(`${this.apiServerUrl}/files/documents/add/${applicationId}`, formData, {
      reportProgress: true,
      observe: 'events'
    });
  }

  download(file:BenefactorDocument): Observable<HttpEvent<Blob>> {
    return this.http.get(`${this.apiServerUrl}/files/documents/download/${file.directory}/${file.fileName}`, {
      reportProgress: true,
      observe: 'events',
      responseType: 'blob'
    });
  }

  acceptedAplicationToAction(aplicationId:number):Observable<string>{
    return this.http.get<string>(`${this.apiServerUrl}/application2charity/process/${aplicationId}/ACCEPTED`)
  }

  rejectAplicationToAction(aplicationId:number):Observable<string>{
    return this.http.get<string>(`${this.apiServerUrl}/application2charity/process/${aplicationId}/REJECTED`)
  }
}
