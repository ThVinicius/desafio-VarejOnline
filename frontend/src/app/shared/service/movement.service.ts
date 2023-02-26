import {Injectable} from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {IMovementDto} from "../type/movement.type";
import {ToastService} from "angular-toastify";
import {FormGroup} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class MovementService {
  private readonly apiUrl = environment.apiUrl
  private readonly headers = {
    headers: new HttpHeaders(this.authService.getAuth())
  }

  constructor(private httpClient: HttpClient,
              private authService: AuthService,
              private toastService: ToastService) {
  }

  postMovement(payload: IMovementDto, form: FormGroup) {
    this.httpClient.post(this.getUrl(payload.movement), payload, this.headers)
      .subscribe(
        () => {
          form.reset()
          this.toastService.success("Movimento cadastrado com sucesso")
        },
        err => {
          switch (err.status) {
            case 422:
              this.toastService.error("A quantidade de estoque é inferior a quantidade solicitada")
              break

            default:
              break
          }
        }
      )
  }

  getUrl(movement: string) {
    switch (movement) {
      case "ENTRADA":
        return `${this.apiUrl}/input-movements`

      case "AJUSTE_ENTRADA":
        return `${this.apiUrl}/input-adjust-movements`

      case "SAÍDA":
        return `${this.apiUrl}/output-movements`

      case "AJUSTE_SAÍDA":
        return `${this.apiUrl}/output-adjust-movements`

      default:
        break
    }
  }
}
