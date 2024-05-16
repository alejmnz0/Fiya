import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Field, FieldListResponse } from "../models/field.interface";
import { AddField } from "../models/add-field.interface";

@Injectable({
    providedIn: 'root',
})
export class FieldService {
    constructor(private http: HttpClient) { }

    getAllFields(page: number): Observable<FieldListResponse> {
        return this.http.get<FieldListResponse>(`http://localhost:8080/field/?page=` + page);
    }

    createField(field: AddField): Observable<Field> {
        return this.http.post<Field>(`http://localhost:8080/field/add`,
            {
                "name": field.name,
                "latitude": field.latitude,
                "longitude": field.longitude,
                "price": field.price,
                "teamCapacity": field.teamCapacity,
                "ground": field.ground
            })
    }

    deleteField(id: number): Observable<any> {
        return this.http.delete<any>(`http://localhost:8080/field/${id}`);
    }
}