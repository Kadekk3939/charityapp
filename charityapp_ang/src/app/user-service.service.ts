import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './user';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient) {}

  public addUser(user:User):Observable<User>{
      return this.http.post<User>(`${environment.apiBaseUrl}/user/add`,user);
  }

  public getUsers():Observable<User[]>{
      return this.http.get<User[]>(`${environment.apiBaseUrl}/user/all`);
  }

  public updateUser(user:User):Observable<User>{
      return this.http.put<User>(`${environment.apiBaseUrl}/user/update`,user);
  }

  public delateteUser(id:number):Observable<void>{
      return this.http.delete<void>(`${environment.apiBaseUrl}/user/delete/${id}`);
  }

  public getUserByLogin(login:string,headers:HttpHeaders):Observable<User>{
      return this.http.get<User>(`${environment.apiBaseUrl}/user/find/login/${login}`, {headers:headers});
  }

}
