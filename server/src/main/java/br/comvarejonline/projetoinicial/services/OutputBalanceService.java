package br.comvarejonline.projetoinicial.services;

import br.comvarejonline.projetoinicial.dto.request.MovementDto;
import br.comvarejonline.projetoinicial.models.OutputBalanceModel;
import br.comvarejonline.projetoinicial.models.ProductModel;
import br.comvarejonline.projetoinicial.repositories.OutputBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutputBalanceService {

    @Autowired
    private OutputBalanceRepository repository;


    public void save(MovementDto body, ProductModel product) {
        assert body.getDocument() != null;
        var movement = new OutputBalanceModel(
                body.getAmount(),
                body.getMovementDate(),
                body.getReason(),
                body.getDocument(),
                product);

        this.repository.save(movement);
    }


}
