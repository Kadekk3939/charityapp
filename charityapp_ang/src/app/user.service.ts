import { User } from './user';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class UserService{
    private apiServerUr1 = environment.apiBaseUrl;

    constructor(private http: HttpClient){}

    public getUsers():Observable<User[]>{
        return this.http.get<User[]>(`${this.apiServerUr1}/user/all`);
    }

    public addUser(user:User):Observable<User>{
        return this.http.post<User>(`${this.apiServerUr1}/user/add`,user);
    }

    public updateUser(user:User):Observable<User>{
        return this.http.put<User>(`${this.apiServerUr1}/user/update`,user);
    }

    public delateteUser(id:number):Observable<void>{
        return this.http.delete<void>(`${this.apiServerUr1}/user/delete/${id}`);
    }
}