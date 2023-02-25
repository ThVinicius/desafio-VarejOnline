package br.comvarejonline.projetoinicial.controllers;

import br.comvarejonline.projetoinicial.dto.request.ProductDto;
import br.comvarejonline.projetoinicial.dto.response.NextProductId;
import br.comvarejonline.projetoinicial.dto.response.ProductResponse;
import br.comvarejonline.projetoinicial.services.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/products")
public class ProductController {
    ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/next-id")
    public NextProductId getNextId() {
        return service.getNextId();
    }

    @PostMapping
    public ProductResponse save(@RequestBody @Valid ProductDto productDto) {
        return service.saveProduct(productDto);
    }

}
