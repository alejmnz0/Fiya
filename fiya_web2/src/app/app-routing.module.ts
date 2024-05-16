import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FieldComponent } from './ui/field/field.component';
import { LoginComponent } from './ui/login/login.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'field', component: FieldComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
