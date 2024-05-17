import { Component, TemplateRef } from '@angular/core';
import { AddUser } from '../../models/add-user.interface';
import { User } from '../../models/user.interface';
import { UserService } from '../../services/user.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent {
  name: string = '';
  email: string = '';
  dni: string = '';
  password: string = '';
  repeatPassword: string = '';
  birthdate!: Date;
  rol: string = '';
  nameErr: string = '';
  emailErr: string = '';
  dniErr: string = '';
  passwordErr: string = '';
  repeatPasswordErr: string = '';
  birthdateErr: string = '';
  rolErr: string = '';
  page = 0;
  userList: User[] = [];
  count = 0;
  pageSize = 0;
  roles: String[] = ["USER", "ADMIN"];

  constructor(private userService: UserService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.loadNewPage();
  }

  open(content: TemplateRef<any>) {
    this.modalService.open(content);
  }

  loadNewPage() {
    this.userService.getAllUsers(this.page - 1).subscribe((fields) => {
      this.userList = fields.content;
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

  crearUser() {
    let newUser: AddUser = new AddUser(this.name, this.dni, this.email, this.password, this.repeatPassword, this.birthdate);
    this.userService.addUser(newUser).subscribe({
      next: resp => {
        window.location.href = "http://localhost:4200/user";
      }, error: errorG => {
        if (errorG.status = 400) {
          let errors = errorG.error.body.fields_errors;
          errors.forEach((erro: { field: any; message: any; }) => {
            switch (erro.field) {
              case "name":
                this.nameErr = erro.message;
                break;
              case "dni":
                this.dniErr = erro.message;
                break;
              case "email":
                this.emailErr = erro.message;
                break;
              case "password":
                this.passwordErr = erro.message;
                break;
              case "repeatPassword":
                this.repeatPasswordErr = erro.message;
                break;
              case "birthdate":
                this.birthdateErr = erro.message;
                break;
            }
          });
        }
      }
    });
  }
}
