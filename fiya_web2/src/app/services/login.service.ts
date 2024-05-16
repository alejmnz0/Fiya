import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  login(dni: string, password: string): Observable<any> {
    return this.http.post(`http://localhost:8080/user/login`,
      {
        "dni": dni,
        "password": password
      }, httpOptions
    );

  }
}
