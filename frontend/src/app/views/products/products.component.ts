import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProductService} from "../../shared/service/product.service";
import {ToastService} from "angular-toastify";
import {AuthService} from "../../shared/service/auth.service";
import {IProductInfo} from "../../shared/type/product.type";
import {MovementService} from "../../shared/service/movement.service";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  productForm: FormGroup

  movementForm: FormGroup

  products: IProductInfo[]
  isProductReady: boolean = false

  minDate = new Date()

  isInputOrOutput: boolean = false


  constructor(private fb: FormBuilder,
              private productService: ProductService,
              private movementService: MovementService,
              private toastService: ToastService,
              public authService: AuthService) {
  }

  ngOnInit() {
    this.authService.checkLogin()
    this.createForm()
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

    this.movementForm = this.fb.group({
      productId: [null, [Validators.required]],
      movement: ["", [Validators.required]],
      amount: [null, [Validators.required]],
      movementDate: [null, [Validators.required]],
      reason: [null, [Validators.required]],
      document: [null, [Validators.required]]
    })

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

  getProducts() {
    this.productService.getProducts().subscribe(
      result => {
        this.products = result
        this.isProductReady = true
      }
    )

  }

  postMovement() {
    if (!this.movementForm.valid) return

    const payload = this.movementForm.value

    console.log(payload)

    this.movementService.postMovement(payload, this.movementForm)
  }

  validAmount(): boolean {
    const minimumAmount = this.productForm.value.minimumAmount
    const openBalance = this.productForm.value.openBalance

    return openBalance >= minimumAmount
  }

  inputOrOutput() {
    const movement = this.movementForm.value.movement

    this.isInputOrOutput = movement === "ENTRADA" || movement === "SAÍDA"
  }

  onChangeDate() {
    const productId: number = this.movementForm.get("productId").value
    const product = this.products.find((item) => item.productId === productId)


    this.minDate = product.registrationDate
  }
}
