package br.comvarejonline.projetoinicial.controllers;

import br.comvarejonline.projetoinicial.dto.exception.ErrorHandle;
import br.comvarejonline.projetoinicial.dto.request.MovementDto;
import br.comvarejonline.projetoinicial.models.ProductModel;
import br.comvarejonline.projetoinicial.services.OutputBalanceService;
import br.comvarejonline.projetoinicial.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/output-movements")
public class OutputBalanceController {

    @Autowired
    private OutputBalanceService service;

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody @Valid MovementDto body) {
        Optional<ProductModel> product = this.productService.findById(body.getProductId());

        if (product.isPresent() && this.productService.validDate(body, product.get())) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("A data da movimentação tem que ser maior ou igual a data de registro do produto");

        }

        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        } else {
            if (this.productService.checkStock(product.get(), body)) {
                ErrorHandle error = new ErrorHandle("amount", "a quantidade é superior a do estoque");

                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
            }

            this.service.save(body, product.get());

            return ResponseEntity.status(HttpStatus.OK).body(body);
        }

    }

}
