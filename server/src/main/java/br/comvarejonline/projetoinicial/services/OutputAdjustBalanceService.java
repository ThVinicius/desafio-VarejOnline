package br.comvarejonline.projetoinicial.services;

import br.comvarejonline.projetoinicial.dto.request.MovementDto;
import br.comvarejonline.projetoinicial.models.OutputAdjustBalanceModel;
import br.comvarejonline.projetoinicial.models.ProductModel;
import br.comvarejonline.projetoinicial.repositories.OutputAdjustBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutputAdjustBalanceService {

    @Autowired
    private OutputAdjustBalanceRepository repository;

    public void save(MovementDto body, ProductModel product) {
        var movement = new OutputAdjustBalanceModel(
                body.getAmount(),
                body.getMovementDate(),
                body.getReason(),
                product);

        this.repository.save(movement);
    }

}
