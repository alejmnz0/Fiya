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
    this.birthdate = this.user.birthdate;
  }

  delete() {
    this.userService.deleteUser(this.user.id).subscribe(ans => {
      window.location.reload();
    });
  }

  edituser() {
    const formattedDate: string = format(this.birthdate, 'MM/dd/yyyy');
    let newuser: EditUser = new EditUser(this.name, this.email, formattedDate);
    this.userService.editUser(this.user.id, newuser).subscribe({
      next: data => {
        this.modalService.dismissAll();
        window.location.reload();
      },

      error: errorG => {
        if (errorG.status = 400) {
          let errors = errorG.error.body.users_errors;
          errors.forEach((erro: { user: any; message: any; }) => {
            switch (erro.user) {
              case "name":
                this.name = erro.message;
                break;
              case "email":
                this.emailErr = erro.message;
                break;
              case "birthdate":
                this.birthdateErr = erro.message;
                break;
            }
          });
        }
      }
    })
  }
}
