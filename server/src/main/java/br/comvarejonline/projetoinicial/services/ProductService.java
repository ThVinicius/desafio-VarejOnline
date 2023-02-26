package br.comvarejonline.projetoinicial.services;

import br.comvarejonline.projetoinicial.dto.request.MovementDto;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public Optional<ProductModel> findById(Long id) {
        return repository.findById(id);
    }


    public NextProductId getNextId() {
        List<ProductModel> products = repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        ProductModel lastProduct = products.stream().findFirst().orElse(null);

        Long nextId;

        if (lastProduct == null) nextId = 1L;
        else nextId = lastProduct.getId() + 1;

        return new NextProductId(nextId);
    }

    public boolean checkStock(ProductModel product, MovementDto movement) {
        Long totalBalance = repository.totalBalance(product.getId());

        return totalBalance - movement.getAmount() < 0;
    }

    public boolean validDate(MovementDto movementDto, ProductModel product) {
        LocalDateTime date = movementDto.getMovementDate()
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);

        return date.isBefore(product.getRegistrationDate());
    }

}
