import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  private dbUrl: string;

  constructor(private http: HttpClient) {
    this.dbUrl = 'http://localhost:8080';
  }

  public addUser(user:User):Observable<User>{
      return this.http.post<User>(`${this.dbUrl}/user/add`,user);
  }

  public getUsers():Observable<User[]>{
      return this.http.get<User[]>(`${this.dbUrl}/user/all`);
  }

  public updateUser(user:User):Observable<User>{
      return this.http.put<User>(`${this.dbUrl}/user/update`,user);
  }

  public delateteUser(id:number):Observable<void>{
      return this.http.delete<void>(`${this.dbUrl}/user/delete/${id}`);
  }

  public getUserByLogin(login:string):Observable<User>{
      return this.http.get<User>(`${this.dbUrl}//find/login/${login}`);
  }

}
