package br.comvarejonline.projetoinicial.services;

import br.comvarejonline.projetoinicial.models.OpenBalanceMovementProduct;
import br.comvarejonline.projetoinicial.repositories.OpenBalanceMovementProductRepository;
import org.springframework.stereotype.Service;

@Service
public class OpenBalanceMovementProductService {

    OpenBalanceMovementProductRepository repository;

    public OpenBalanceMovementProductService(OpenBalanceMovementProductRepository repository) {
        this.repository = repository;
    }

    public void saveOpenBalanceMovement(OpenBalanceMovementProduct openBalanceMovementProduct) {
        repository.save(openBalanceMovementProduct);
    }

}
