import { Component, Input, TemplateRef } from '@angular/core';
import { Team } from '../../models/team.interface';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TeamService } from '../../services/team.service';
import { EditTeam } from '../../models/edit-team.interface';

@Component({
  selector: 'app-team-item',
  templateUrl: './team-item.component.html',
  styleUrl: './team-item.component.css'
})
export class TeamItemComponent {
  name: string = '';
  logo: string = '';
  primaryColor: number = 0;
  secondaryColor: number = 0;
  actualName: string = '';
  logoErr: string = '';
  nameErr: string = '';
  primaryColorErr: string = '';
  secondaryColorErr: string = '';
  @Input() team!: Team;


  constructor(private modalService: NgbModal, private teamService: TeamService) { }

  popoverClicked(event: MouseEvent) {
    event.stopPropagation();
  }

  open(content: TemplateRef<any>) {
    this.modalService.open(content);
    this.name = this.team.name;
    this.logo = this.team.urlImage;
    this.actualName = this.team.name;
    this.primaryColor = this.team.primaryColor;
    this.secondaryColor = this.team.secondaryColor;
  }

  fixColor(color: number): string {
    let binaryColor = (color >>> 0).toString(2);
    const argb: number[] = [];
    for (let i = 0; i < 32; i += 8) {
      const segment = binaryColor.slice(i, i + 8);
      argb.push(parseInt(segment, 2));
    }
    return `rgb(${argb[1]},${argb[2]},${argb[3]})`
  }

  delete() {
    this.teamService.deleteTeam(this.team.id).subscribe(ans => {
      window.location.reload();
    });
  }

  openDeleteModal(content: TemplateRef<any>) {
    this.modalService.open(content);
  }

  editTeam() {
    let newTeam: EditTeam = new EditTeam(this.name, this.logo, this.secondaryColor, this.primaryColor, this.actualName);
    this.teamService.editTeam(this.team.id, newTeam).subscribe({
      next: data => {
        this.modalService.dismissAll();
        window.location.reload();
      },
      error: errorG => {
        if (errorG.status = 400) {
          let errors = errorG.error.body.fields_errors;
          errors.forEach((erro: { field: any; message: any; }) => {
            switch (erro.field) {
              case "name":
                this.nameErr = erro.message;
                break;
              case "logo":
                this.logoErr = erro.message;
                break;
              case "primaryColor":
                this.primaryColorErr = erro.message;
                break;
              case "secondaryColor":
                this.secondaryColorErr = erro.message;
                break;
              default:
                this.nameErr = erro.message
                break;
            }
          });
        }
      }
    })
  }

}
