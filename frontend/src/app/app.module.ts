import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule, NoopAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AppRoutingModule} from "./app-routing.module";
import {AppComponent} from "./app.component";
import {AngularToastifyModule, ToastService} from 'angular-toastify';
import {ProductsComponent} from './views/products/products.component';
import {LoginComponent} from './views/login/login.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from "@angular/material/icon";
import {MatTabsModule} from "@angular/material/tabs";
import {MatCardModule} from "@angular/material/card";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {MatSelectModule} from "@angular/material/select";
import {MatOptionModule} from "@angular/material/core";


@NgModule({
  declarations: [AppComponent, LoginComponent, ProductsComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NoopAnimationsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AngularToastifyModule,
    MatToolbarModule,
    MatIconModule,
    MatTabsModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatOptionModule
  ],
  providers: [ToastService],
  bootstrap: [AppComponent],
})
export class AppModule {
}
