import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AddUser } from '../models/add-user.interface';
import { Observable } from 'rxjs';
import { User, UserListResponse } from '../models/user.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getAllUsers(page: number): Observable<UserListResponse> {
    return this.http.get<UserListResponse>(`http://localhost:8080/user/?page=` + page)
  }

  addUser(user: AddUser): Observable<User> {
    return this.http.post<User>(`http://localhost:8080/user/register`,
      {
        "dni": user.dni,
        "email": user.email,
        "name": user.name,
        "password": user.password,
        "repeatPassword": user.repeatPassword,
        "birthdate": user.birthdate
      }
    )
  }
}
