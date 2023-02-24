package br.comvarejonline.projetoinicial.controllers;

import br.comvarejonline.projetoinicial.dto.response.NextProductId;
import br.comvarejonline.projetoinicial.services.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/products")
public class ProductController {
    ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/next-id")
    public NextProductId getNextId (){
        return service.getNextId();
    }

}
