import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FieldListResponse } from '../models/field.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FieldService {

  constructor(private http: HttpClient) { }

  getAllFields(page: number): Observable<FieldListResponse> {
    return this.http.get<FieldListResponse>(`http://localhost:8080/field/?page` + page);
  }
}
