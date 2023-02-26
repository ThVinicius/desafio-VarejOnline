package br.comvarejonline.projetoinicial.services;

import br.comvarejonline.projetoinicial.dto.request.ProductDto;
import br.comvarejonline.projetoinicial.dto.response.NextProductId;
import br.comvarejonline.projetoinicial.dto.response.ProductInfo;
import br.comvarejonline.projetoinicial.dto.response.ProductResponse;
import br.comvarejonline.projetoinicial.models.OpenBalanceModel;
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

    private final OpenBalanceService openBalanceService;

    public ProductService(ProductRepository repository, OpenBalanceService openBalanceService) {
        this.repository = repository;
        this.openBalanceService = openBalanceService;
    }

    @Transactional
    public ProductResponse saveProduct(ProductDto productDto) {
        var product = new ProductModel(productDto.getName(), productDto.getBarCode(), productDto.getMinimumAmount());
        ProductModel productSave = repository.save(product);

        var openBalance = new OpenBalanceModel(productDto.getOpenBalance(), productSave);
        openBalanceService.saveOpenBalance(openBalance);

        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(productSave, productResponse);
        productResponse.setOpenBalance(productDto.getOpenBalance());

        return productResponse;
    }

    public List<ProductInfo> findAll() {
        return repository.findAllWithInfo();
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
