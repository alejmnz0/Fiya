import { Component } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  id = sessionStorage.getItem('id');

  logout() {
    sessionStorage.clear();
  }
}
