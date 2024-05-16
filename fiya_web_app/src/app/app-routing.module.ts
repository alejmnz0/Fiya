import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PruebasComponent } from './components/pruebas/pruebas.component';
import { LoginComponent } from './ui/login/login.component';
import { FieldsComponent } from './ui/fields/fields.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'fields', component: FieldsComponent },
  { path: '', pathMatch: 'full', component: PruebasComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
