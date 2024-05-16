import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

export class UserService {

    constructor(private http: HttpClient) { }

    login(dni: any, password: any): Observable<any> {
        return this.http.post('localhost:8080/user/login',
            {
                "dni": dni,
                "password": password
            }, httpOptions);
    }
}