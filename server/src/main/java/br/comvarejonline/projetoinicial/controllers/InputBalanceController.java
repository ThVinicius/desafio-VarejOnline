package br.comvarejonline.projetoinicial.controllers;

import br.comvarejonline.projetoinicial.dto.request.MovementDto;
import br.comvarejonline.projetoinicial.models.ProductModel;
import br.comvarejonline.projetoinicial.services.InputBalanceService;
import br.comvarejonline.projetoinicial.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/input-movements")
public class InputBalanceController {

    @Autowired
    private InputBalanceService service;

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
            this.service.save(body, product.get());

            return ResponseEntity.status(HttpStatus.OK).body(body);
        }

    }

}
