import { Component, Input } from '@angular/core';
import { Team } from '../../models/team.interface';

@Component({
  selector: 'app-team-list',
  templateUrl: './team-list.component.html',
  styleUrl: './team-list.component.css'
})
export class TeamListComponent {

  @Input() teamList!: Team[];
}
