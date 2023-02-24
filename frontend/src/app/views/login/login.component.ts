import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LoginService} from "../../shared/service/login.service";
import {ToastService} from "angular-toastify";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder,
              private service: LoginService,
  ) {
  }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.loginForm = this.fb.group({
      username: ["", [Validators.required]],
      password: ["", [Validators.required]],
    });
  }

  onSubmit() {
    const username: string = this.loginForm.get("username").value
    const password: string = this.loginForm.get("password").value
    const auth = {Authorization: "Basic " + btoa(`${username}:${password}`)}

    this.service.signIn(auth)
  }

}
