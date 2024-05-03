import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PruebasComponent } from './components/pruebas/pruebas.component';
import { FieldsComponent } from './ui/fields/fields.component';
import { LoginComponent } from './ui/login/login.component';
import { FieldListComponent } from './components/field-list/field-list.component';
import { FieldItemComponent } from './components/field-item/field-item.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    PruebasComponent,
    FieldsComponent,
    LoginComponent,
    FieldListComponent,
    FieldItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
