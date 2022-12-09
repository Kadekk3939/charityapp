import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  static dbUrl: string;

  constructor(private http: HttpClient) {
    UserServiceService.dbUrl = 'http://localhost:8080';
  }

  public addUser(user:User):Observable<User>{
      return this.http.post<User>(`${UserServiceService.dbUrl}/user/add`,user);
  }

  public getUsers():Observable<User[]>{
      return this.http.get<User[]>(`${UserServiceService.dbUrl}/user/all`);
  }

  public updateUser(user:User):Observable<User>{
      return this.http.put<User>(`${UserServiceService.dbUrl}/user/update`,user);
  }

  public delateteUser(id:number):Observable<void>{
      return this.http.delete<void>(`${UserServiceService.dbUrl}/user/delete/${id}`);
  }

  public getUserByLogin(login:string):Observable<User>{
      return this.http.get<User>(`${UserServiceService.dbUrl}/find/login/${login}`);
  }

}
