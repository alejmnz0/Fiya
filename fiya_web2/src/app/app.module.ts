import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FieldComponent } from './ui/field/field.component';
import { FieldItemComponent } from './component/field-item/field-item.component';
import { FieldListComponent } from './component/field-list/field-list.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './ui/login/login.component';
import { UserComponent } from './ui/user/user.component';
import { UserListComponent } from './component/user-list/user-list.component';
import { UserItemComponent } from './component/user-item/user-item.component';
import { NavbarComponent } from './component/navbar/navbar.component';
import { ColorPickerModule } from 'ngx-color-picker';
import { TeamComponent } from './ui/team/team.component';
import { TeamListComponent } from './component/team-list/team-list.component';
import { TeamItemComponent } from './component/team-item/team-item.component';
import { TeamPlayersComponent } from './ui/team-players/team-players.component';

@NgModule({
  declarations: [
    AppComponent,
    FieldComponent,
    FieldItemComponent,
    FieldListComponent,
    LoginComponent,
    UserComponent,
    UserListComponent,
    UserItemComponent,
    NavbarComponent,
    TeamComponent,
    TeamListComponent,
    TeamItemComponent,
    TeamPlayersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    ColorPickerModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
