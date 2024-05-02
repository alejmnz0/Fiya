import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { FieldListResponse } from "../models/field.interface";

@Injectable({
    providedIn: 'root',
})
export class FieldService {
    constructor(private http: HttpClient) { }

    getAllFields(page: number): Observable<FieldListResponse> {
        return this.http.get<FieldListResponse>(`http://localhost:8080/field/?page` + page);
    }
}