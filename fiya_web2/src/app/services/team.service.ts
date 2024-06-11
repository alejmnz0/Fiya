import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Team, TeamListResponse } from '../models/team.interface';
import { Observable } from 'rxjs';
import { AddTeam } from '../models/add-team.interface';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  constructor(private http: HttpClient) { }

  getAllTeams(page: number): Observable<TeamListResponse> {
    return this.http.get<TeamListResponse>(`http://localhost:8080/team/?page=` + page);
  }

  addTeam(team: AddTeam): Observable<Team> {
    return this.http.post<Team>(`http://localhost:8080/team/add`,
      {
        "name": team.name,
        "urlImage": team.urlImage,
        "primaryColor": team.primaryColor,
        "secondaryColor": team.secundaryColor
      }
    )
  }
}
