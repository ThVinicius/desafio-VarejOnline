import {Injectable} from '@angular/core';
import {Router} from "@angular/router";
import {ToastService} from "angular-toastify";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private auth: { Authorization: string }

  private authority: string

  constructor(private router: Router,
              private toastService: ToastService) {
  }

  checkAuthority(authorities: string[]): boolean {
    return authorities.some((value) => value === this.authority)
  }

  checkLogin() {
    const auth = this.auth

    if (!auth) {
      this.router.navigateByUrl("/")
      this.toastService.error("É necessário estar logado para acessar essa rota")
    }
  }

  setAuth(auth: { Authorization: string }) {
    this.auth = auth
  }

  getAuth() {
    return this.auth
  }

  setAuthority(authority: string) {
    this.authority = authority
  }

}
