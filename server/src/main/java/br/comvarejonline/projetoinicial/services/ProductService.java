package br.comvarejonline.projetoinicial.services;

import br.comvarejonline.projetoinicial.dto.request.ProductDto;
import br.comvarejonline.projetoinicial.dto.response.NextProductId;
import br.comvarejonline.projetoinicial.dto.response.ProductResponse;
import br.comvarejonline.projetoinicial.models.OpenBalanceMovementProduct;
import br.comvarejonline.projetoinicial.models.ProductModel;
import br.comvarejonline.projetoinicial.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    private final OpenBalanceMovementProductService openBalanceMovementProductService;

    public ProductService(ProductRepository repository, OpenBalanceMovementProductService openBalanceMovementProductService) {
        this.repository = repository;
        this.openBalanceMovementProductService = openBalanceMovementProductService;
    }

    @Transactional
    public ProductResponse saveProduct(ProductDto productDto) {
        var product = new ProductModel(productDto.getName(), productDto.getBarCode(), productDto.getMinimumAmount());
        ProductModel productSave = repository.save(product);

        var openBalanceMovementProduct = new OpenBalanceMovementProduct(productDto.getOpenBalanceMovement(), productSave);
        openBalanceMovementProductService.saveOpenBalanceMovement(openBalanceMovementProduct);

        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(productSave, productResponse);
        productResponse.setOpenBalanceMovementProduct(productDto.getOpenBalanceMovement());

        return productResponse;
    }


    public NextProductId getNextId() {
        List<ProductModel> products = repository.findAll(Sort.by(Sort.Direction.DESC, "productId"));
        ProductModel lastProduct = products.stream().findFirst().orElse(null);

        Long nextId;

        if (lastProduct == null) nextId = 1L;
        else nextId = lastProduct.getProductId() + 1;

        return new NextProductId(nextId);
    }

}
