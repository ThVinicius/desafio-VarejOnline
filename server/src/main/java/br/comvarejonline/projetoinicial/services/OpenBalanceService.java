package br.comvarejonline.projetoinicial.services;

import br.comvarejonline.projetoinicial.models.OpenBalanceModel;
import br.comvarejonline.projetoinicial.repositories.OpenBalanceRepository;
import org.springframework.stereotype.Service;

@Service
public class OpenBalanceService {

    OpenBalanceRepository repository;

    public OpenBalanceService(OpenBalanceRepository repository) {
        this.repository = repository;
    }

    public void saveOpenBalance(OpenBalanceModel openBalanceMovement) {
        repository.save(openBalanceMovement);
    }

}
