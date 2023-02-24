package br.comvarejonline.projetoinicial.config;

import br.comvarejonline.projetoinicial.enums.RoleName;
import br.comvarejonline.projetoinicial.models.RoleModel;
import br.comvarejonline.projetoinicial.models.UserModel;
import br.comvarejonline.projetoinicial.repositories.RoleRepository;
import br.comvarejonline.projetoinicial.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Seed implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private RoleModel gerenteRole;

    private RoleModel operadorRole;

    public Seed(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedRoles();
        this.seedUsers();
    }

    private void seedRoles() {
        RoleModel gerente = new RoleModel(RoleName.valueOf("GERENTE"));
        this.gerenteRole = roleRepository.save(gerente);

        RoleModel operador = new RoleModel(RoleName.valueOf("OPERADOR"));
        this.operadorRole = roleRepository.save(operador);
    }

    private void seedUsers() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("123");

        List<RoleModel> gerenteRoles = new ArrayList<>();
        gerenteRoles.add(this.gerenteRole);
        UserModel gerente = new UserModel("gerente", password, gerenteRoles);
        userRepository.save(gerente);

        List<RoleModel> operadorRoles = new ArrayList<>();
        operadorRoles.add(this.operadorRole);
        UserModel operador = new UserModel("operador", password, operadorRoles);
        userRepository.save(operador);
    }


}
