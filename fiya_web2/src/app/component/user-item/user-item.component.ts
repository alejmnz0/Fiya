import { Component, Input, TemplateRef } from '@angular/core';
import { UserService } from '../../services/user.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../../models/user.interface';
import { EditUser } from '../../models/edit-user.interface';
import { format } from 'date-fns';

@Component({
  selector: 'app-user-item',
  templateUrl: './user-item.component.html',
  styleUrl: './user-item.component.css'
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


  constructor(private modalService: NgbModal, private userService: UserService) { }

  popoverClicked(event: MouseEvent) {
    event.stopPropagation();
  }

  open(content: TemplateRef<any>) {
    this.modalService.open(content);
    this.name = this.user.name;
    this.email = this.user.email;
    this.actualEmail = this.user.email;
    this.birthdate = this.user.birthdate;
  }

  delete() {
    this.userService.deleteUser(this.user.id).subscribe(ans => {
      window.location.reload();
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
        if (errorG.status = 400) {
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
                this.emailErr = erro.message
                break;
            }
          });
        }
      }
    })
  }
}
