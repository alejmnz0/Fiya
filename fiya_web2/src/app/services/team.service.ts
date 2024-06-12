import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Team, TeamListResponse } from '../models/team.interface';
import { Observable } from 'rxjs';
import { AddTeam } from '../models/add-team.interface';
import { EditTeam } from '../models/edit-team.interface';

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

  deleteTeam(id: number): Observable<any> {
    return this.http.delete<any>(`http://localhost:8080/team/${id}`);
  }

  editTeam(id: number, team: EditTeam): Observable<EditTeam> {
    return this.http.put<EditTeam>(`http://localhost:8080/team/${id}/edit`,
      {
        "name": team.name,
        "actualName": team.actualName,
        "urlImage": team.urlImage,
        "primaryColor": team.primaryColor,
        "secondaryColor": team.secondaryColor,
      }
    )
  }
}
