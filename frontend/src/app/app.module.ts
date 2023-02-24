import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { NoopAnimationsModule } from "@angular/platform-browser/animations";
import { ToastService, AngularToastifyModule } from 'angular-toastify';
import { ProductsComponent } from './views/products/products.component';
import { LoginComponent } from './views/login/login.component';


@NgModule({
  declarations: [AppComponent, LoginComponent,ProductsComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NoopAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AngularToastifyModule
  ],
  providers: [ToastService],
  bootstrap: [AppComponent],
})
export class AppModule {}
