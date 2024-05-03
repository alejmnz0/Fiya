import { NgModule } from "@angular/core";
import { FieldComponent } from "../ui/field/field.component";
import { LoginComponent } from "../ui/login/login.component";
import { RouterModule, Routes } from "@angular/router";
import { PruebasComponent } from "./components/pruebas/pruebas.component";

const routes: Routes = [
    { path: '', component: PruebasComponent, pathMatch: 'full' },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule { }