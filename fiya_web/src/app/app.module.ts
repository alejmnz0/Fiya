import { NgModule } from '@angular/core';
import {
    BrowserModule,
} from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule, provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { LoggerInterceptor } from './logger.interceptor';
import { RemoveWrapperInterceptor } from './RemoveWrapperInterceptor';
import { LoginComponent } from '../ui/login/login.component';
import { FieldItemComponent } from '../components/field-item/field-item.component';
import { FieldListComponent } from '../components/field-list/field-list.component';
import { FieldComponent } from '../ui/field/field.component';
import { AppComponent } from './app.component';
import { PruebasComponent } from './components/pruebas/pruebas.component';

@NgModule({
    declarations: [
        AppComponent,
        PruebasComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        NgbModule,
        HttpClientModule,
        FormsModule,
    ],
    providers: [],
    bootstrap: [AppComponent],
})
export class AppModule {
}