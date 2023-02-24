import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private auth: { Authorization: string }

  private authority: string

  setAuth(auth: { Authorization: string }) {
    this.auth = auth
  }

  getAuth() {
    return this.auth
  }

  setAuthority(authority: string) {
    this.authority = authority
  }

  getAuthority() {
    return this.authority
  }
}
