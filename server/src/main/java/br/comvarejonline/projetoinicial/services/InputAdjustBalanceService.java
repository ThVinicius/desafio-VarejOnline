package br.comvarejonline.projetoinicial.services;

import br.comvarejonline.projetoinicial.dto.request.MovementDto;
import br.comvarejonline.projetoinicial.models.InputAdjustBalanceModel;
import br.comvarejonline.projetoinicial.models.ProductModel;
import br.comvarejonline.projetoinicial.repositories.InputAdjustBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputAdjustBalanceService {

    @Autowired
    private InputAdjustBalanceRepository repository;

    public void save(MovementDto body, ProductModel product) {
        var movement = new InputAdjustBalanceModel(
                body.getAmount(),
                body.getMovementDate(),
                body.getReason(),
                product);

        this.repository.save(movement);
    }

}
