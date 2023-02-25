import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProductService} from "../../shared/service/product.service";
import {ToastService} from "angular-toastify";
import {AuthService} from "../../shared/service/auth.service";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  productForm: FormGroup

  selectedValue: string;

  foods = [
    {value: 'steak-0', viewValue: 'Steak'},
    {value: 'pizza-1', viewValue: 'Pizza'},
    {value: 'tacos-2', viewValue: 'Tacos'},
  ];


  constructor(private fb: FormBuilder,
              private productService: ProductService,
              private toastService: ToastService,
              public authService: AuthService) {
  }

  ngOnInit() {
    this.authService.checkLogin()
    this.createForm();
    this.productService.getNextProductId()
      .subscribe(result =>
        this.productForm.get("productId").setValue(result.nextProductId)
      )
  }

  createForm() {
    this.productForm = this.fb.group({
      productId: {value: "", disabled: true},
      barCode: ["", [Validators.required]],
      name: ["", [Validators.required]],
      minimumAmount: ["", [Validators.required, Validators.pattern('^[0-9]*$'), Validators.min(1)]],
      openBalance: ["", [Validators.required, Validators.pattern('^[0-9]*$'), Validators.min(1)]]
    });

  }

  postProduct() {
    if (!this.productForm.valid) return
    if (!this.validAmount()) {
      this.toastService.error("Saldo inicial tem que ser maior ou igual a quantidade mínima")
      return
    }
    const formValue = this.productForm.value
    delete formValue.productId

    this.productService.postProduct(formValue, this.productForm)
  }

  validAmount(): boolean {
    const minimumAmount = this.productForm.value.minimumAmount
    const openBalance = this.productForm.value.openBalance

    return openBalance >= minimumAmount
  }
}
