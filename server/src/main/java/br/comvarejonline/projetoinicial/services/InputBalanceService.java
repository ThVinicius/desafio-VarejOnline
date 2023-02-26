package br.comvarejonline.projetoinicial.services;

import br.comvarejonline.projetoinicial.dto.request.MovementDto;
import br.comvarejonline.projetoinicial.models.InputBalanceModel;
import br.comvarejonline.projetoinicial.models.ProductModel;
import br.comvarejonline.projetoinicial.repositories.InputBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputBalanceService {

    @Autowired
    private InputBalanceRepository repository;

    public void save(MovementDto body, ProductModel product) {
        System.out.println("Entrou no serviço");
        assert body.getDocument() != null;
        var movement = new InputBalanceModel(
                body.getAmount(),
                body.getMovementDate(),
                body.getReason(),
                body.getDocument(),
                product);
        System.out.println("Instânciou o objeto InputBalanceModel");

        this.repository.save(movement);
    }

}
