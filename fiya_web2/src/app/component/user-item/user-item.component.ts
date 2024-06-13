import { Component, Input, TemplateRef } from '@angular/core';
import { UserService } from '../../services/user.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../../models/user.interface';
import { EditUser } from '../../models/edit-user.interface';
import { format } from 'date-fns';
import { Team } from '../../models/team.interface';
import { TeamService } from '../../services/team.service';
import { AddPlayer } from '../../models/add-player.interface';

@Component({
  selector: 'app-user-item',
  templateUrl: './user-item.component.html',
  styleUrls: ['./user-item.component.css'] // Cambiado styleUrl a styleUrls
})
export class UserItemComponent {
  name: string = '';
  email: string = '';
  actualEmail: string = '';
  dni: string = '';
  birthdate!: Date;
  nameErr: string = '';
  emailErr: string = '';
  dniErr: string = '';
  birthdateErr: string = '';
  @Input() user!: User;
  roles: String[] = ["USER", "ADMIN"];
  teams!: Team[];

  constructor(private modalService: NgbModal, private userService: UserService, private teamService: TeamService) { }

  ngOnInit(): void {
    this.loadTeams();
  }

  popoverClicked(event: MouseEvent) {
    event.stopPropagation();
  }

  loadTeams() {
    this.teamService.getAllTeams(0).subscribe({
      next: (fields) => {
        this.teams = fields.content;
      },
      error: (error) => {
        console.error('Error loading teams', error);
      }
    });
  }

  open(content: TemplateRef<any>) {
    this.modalService.open(content);
    this.name = this.user.name;
    this.email = this.user.email;
    this.actualEmail = this.user.email;
    this.birthdate = this.user.birthdate;
  }

  delete() {
    this.userService.deleteUser(this.user.id).subscribe({
      next: () => {
        window.location.reload();
      },
      error: (error) => {
        console.error('Error deleting user', error);
      }
    });
  }

  associateTeam(content: any) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then(
      (result) => {
      },
      (reason) => {
      }
    );
  }

  selectTeam(teamId: number) {
    this.teamService.addUser(new AddPlayer(teamId, this.user.id)).subscribe({
      next: () => {
        this.modalService.dismissAll();
        window.location.reload();
      },
      error: (error) => {
        console.error('Error adding user to team', error);
        if (error.status === 401 || error.status === 403) {
          console.error('Authentication error');
        }
      }
    });
  }

  deleteTeam(user: User) {
    this.teamService.deleteUser(new AddPlayer(user.team.id, this.user.id)).subscribe({
      next: () => {
        this.modalService.dismissAll();
        window.location.reload();
      },
      error: (error) => {
        console.error('Error adding user to team', error);
        if (error.status === 401 || error.status === 403) {
          console.error('Authentication error');
        }
      }
    });
  }

  edituser() {
    const formattedDate: string = format(this.birthdate, 'MM/dd/yyyy');
    let newuser: EditUser = new EditUser(this.name, this.email, formattedDate, this.actualEmail);
    this.userService.editUser(this.user.id, newuser).subscribe({
      next: data => {
        this.modalService.dismissAll();
        window.location.reload();
      },
      error: errorG => {
        if (errorG.status === 400) {
          let errors = errorG.error.body.fields_errors;
          errors.forEach((erro: { field: any; message: any; }) => {
            switch (erro.field) {
              case "name":
                this.nameErr = erro.message;
                break;
              case "email":
                this.emailErr = erro.message;
                break;
              case "birthdate":
                this.birthdateErr = erro.message;
                break;
              default:
                this.emailErr = erro.message;
                break;
            }
          });
        }
      }
    });
  }
}
