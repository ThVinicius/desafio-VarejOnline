import { Injectable } from "@angular/core";
import axios from "axios";
import { environment } from "../../../environments/environment";

@Injectable({
  providedIn: "root",
})
export class LoginService {
  private readonly apiUrl = environment.apiUrl;

  constructor() {}
}
