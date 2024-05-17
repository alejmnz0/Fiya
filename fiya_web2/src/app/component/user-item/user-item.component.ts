import { Component, Input, TemplateRef } from '@angular/core';
import { UserService } from '../../services/user.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../../models/user.interface';

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
    this.dni = this.user.dni;
    this.birthdate = this.user.birthdate;
  }

  // delete() {
  //   this.userService.deleteuser(this.user.id).subscribe(ans => {
  //     window.location.reload();
  //   });
  // }

  // edituser() {
  //   let newuser: Edituser = new Edituser(this.nombre, this.latitud, this.longitud, this.price, this.teamCapacity, this.ground, this.description);
  //   this.userService.edituser(this.user.id, newuser).subscribe({
  //     next: data => {
  //       this.modalService.dismissAll();
  //       window.location.reload();
  //     },

  //     error: errorG => {
  //       if (errorG.status = 400) {
  //         let errors = errorG.error.body.users_errors;
  //         errors.forEach((erro: { user: any; message: any; }) => {
  //           switch (erro.user) {
  //             case "name":
  //               this.nombreErr = erro.message;
  //               break;
  //             case "latitude":
  //               this.latitudErr = erro.message;
  //               break;
  //             case "longitude":
  //               this.longitudErr = erro.message;
  //               break;
  //             case "price":
  //               this.priceErr = erro.message;
  //               break;
  //             case "teamCapacity":
  //               this.teamCapacityErr = erro.message;
  //               break;
  //             case "ground":
  //               this.groundErr = erro.message;
  //               break;
  //             case "description":
  //               this.descriptionErr = erro.message;
  //               break;
  //           }
  //         });
  //       }
  //     }
  //   })
  // }
}
