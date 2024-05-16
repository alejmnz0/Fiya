import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AddUser } from '../models/add-user.interface';
import { Observable } from 'rxjs';
import { User } from '../models/user.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  addUser(user: AddUser): Observable<User> {

  }
}
