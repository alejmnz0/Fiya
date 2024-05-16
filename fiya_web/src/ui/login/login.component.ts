import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { TokenStorageService } from '../../services/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  dni!: any;
  password!: any;
  errorMessage: string = ""

  constructor(private userService: UserService, private tokenService: TokenStorageService) { }

  login() {
    this.userService.login(this.dni, this.password).subscribe({
      next: data => {
        //this.tokenStorage.saveToken(data.accessToken);
        this.tokenService.saveToken(data.token);
        this.tokenService.saveUser(data);
        sessionStorage.setItem('rol', data.rol);
        //window.location.href = `${environment.localHost}subjects`
      },
      error: err => {
        this.errorMessage = "Las credenciales son inválidas";
      }
    });
  }

}
