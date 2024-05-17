import { Component } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { TokenService } from '../../services/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  username!: any;
  password!: any;
  errorMessage: string = ""

  constructor(private loginService: LoginService, private tokenService: TokenService) { }

  login() {
    this.loginService.login(this.username, this.password).subscribe({
      next: data => {
        this.tokenService.saveToken(data.token);
        this.tokenService.saveUser(data);
        sessionStorage.setItem('rol', data.rol);
        window.location.href = `http://localhost:4200/field`
      },
      error: err => {
        this.errorMessage = "Invalid data";
      }
    });
  }

}
