import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from "../../../environments/environment";
import {AuthService} from "./auth.service";
import {ToastService} from "angular-toastify";
import {Router} from "@angular/router";

@Injectable({
  providedIn: "root",
})
export class LoginService {
  private readonly apiUrl = environment.apiUrl;

  constructor(private httpClient: HttpClient,
              private authService: AuthService,
              private router: Router,
              private toastService: ToastService) {
  }

  signIn(auth: { Authorization: string }) {
    const headers = {headers: new HttpHeaders(auth)}

    this.httpClient.post(`${this.apiUrl}/sign-in`, {}, headers)
      .subscribe(
        result => {
          const authority: string = result[0].authority
          this.authService.setAuthority(authority)
          this.authService.setAuth(auth)
          this.toastService.success("Login feito com sucesso!")
          this.router.navigateByUrl('/products')
        },
        err => {
          switch (err.status) {
            case 401:
              this.toastService.error("Usuário ou senha incorreto")
              break

            default:
              this.toastService.error("Ocorreu um erro no servidor!")
              break
          }

        })


  }

}
