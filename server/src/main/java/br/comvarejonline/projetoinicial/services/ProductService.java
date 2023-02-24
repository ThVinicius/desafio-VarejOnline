package br.comvarejonline.projetoinicial.services;

import br.comvarejonline.projetoinicial.dto.response.NextProductId;
import br.comvarejonline.projetoinicial.models.ProductModel;
import br.comvarejonline.projetoinicial.repositories.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public NextProductId getNextId (){
        List<ProductModel> products = repository.findAll(Sort.by(Sort.Direction.DESC, "productId"));
        ProductModel lastProduct = products.stream().findFirst().orElse(null);

        Long nextId;

        if (lastProduct == null) nextId = 1L;
        else nextId = lastProduct.getProductId() + 1;

        return new NextProductId(nextId);
    }

}
