import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FieldComponent } from './ui/field/field.component';
import { LoginComponent } from './ui/login/login.component';
import { UserComponent } from './ui/user/user.component';
import { TeamComponent } from './ui/team/team.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'field', component: FieldComponent },
  { path: 'user', component: UserComponent },
  { path: 'team', component: TeamComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
