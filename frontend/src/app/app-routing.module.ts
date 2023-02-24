import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {ProductsComponent} from "./views/products/products.component";
import {LoginComponent} from "./views/login/login.component";

const routes: Routes = [
  {path: "", component: LoginComponent},
  {path: "products", component: ProductsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
