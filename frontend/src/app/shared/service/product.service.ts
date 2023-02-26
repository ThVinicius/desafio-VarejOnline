import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {Router} from "@angular/router";
import {ToastService} from "angular-toastify";
import {environment} from "../../../environments/environment";
import {INextProductId, IProduct, IProductInfo} from "../type/product.type";
import {FormGroup} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private readonly apiUrl = `${environment.apiUrl}/products`;
  private readonly headers = {headers: new HttpHeaders(this.authService.getAuth())}

  constructor(private httpClient: HttpClient,
              private authService: AuthService,
              private router: Router,
              private toastService: ToastService) {
  }

  getNextProductId() {
    return this.httpClient.get<INextProductId>(`${this.apiUrl}/next-id`, this.headers)
  }

  postProduct(product: IProduct, form: FormGroup) {
    this.httpClient.post(this.apiUrl, product, this.headers)
      .subscribe(() => {
          this.toastService.success("produto cadastrado com sucesso!")
          form.reset()
          this.getNextProductId().subscribe(
            result => form.get("productId").setValue(result.nextProductId))

        }
        ,
        err => {
          switch (err.status) {
            case 401:
              this.toastService.error("Usuário ou senha incorreto")
              break

            case 409:
              this.toastService.error("Esse código de barras já está cadastrado!")
              break

            default:
              this.toastService.error("Ocorreu um erro no servidor!")
              break
          }
        })
  }

  getProducts() {
    return this.httpClient.get<IProductInfo[]>(this.apiUrl, this.headers)
  }

}

