import { Component, TemplateRef } from '@angular/core';
import { Team } from '../../models/team.interface';
import { TeamService } from '../../services/team.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AddTeam } from '../../models/add-team.interface';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrl: './team.component.css'
})
export class TeamComponent {

  nombre: string = '';
  logo: string = '';
  primaryColor: number = 0;
  secondaryColor: number = 0;
  nombreErr: string = '';
  logoErr: string = '';
  primaryColorErr: string = '';
  secondaryColorErr: string = '';
  page = 0;
  teamList: Team[] = [];
  count = 0;
  pageSize = 0;

  constructor(private teamService: TeamService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.loadNewPage();
  }

  open(content: TemplateRef<any>) {
    this.modalService.open(content);
  }

  loadNewPage() {
    this.teamService.getAllTeams(this.page - 1).subscribe((fields) => {
      this.teamList = fields.content;
      this.pageSize = fields.size;
      if (fields.totalElements > 1000) {
        this.count = 10000;
      } else {
        this.count = fields.totalElements;
      }
      window.scrollTo({ top: 0, behavior: 'smooth' });
    }
    )
  }

  crearTeam() {
    console.log(this.primaryColor)
    let newTeam: AddTeam = new AddTeam(this.nombre, this.logo, this.primaryColor, this.secondaryColor);
    this.teamService.addTeam(newTeam).subscribe({
      next: resp => {
        window.location.href = "http://localhost:4200/team";
      }, error: errorG => {
        if (errorG.status = 400) {
          let errors = errorG.error.body.fields_errors;
          errors.forEach((erro: { field: any; message: any; }) => {
            switch (erro.field) {
              case "name":
                this.nombreErr = erro.message;
                break;
              case "urlImage":
                this.logoErr = erro.message;
                break;
              case "primaryColor":
                this.primaryColorErr = erro.message;
                break;
              case "secondaryColor":
                this.secondaryColorErr = erro.message;
                break;
            }
          });
        }
      }
    });
  }

}
