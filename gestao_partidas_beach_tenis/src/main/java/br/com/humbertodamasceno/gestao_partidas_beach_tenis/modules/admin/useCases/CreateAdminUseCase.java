package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.admin.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.exceptions.AdminAlreadyExist;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.admin.entities.AdminEntity;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.admin.repositories.AdminRepositoty;

@Service
public class CreateAdminUseCase {

    @Autowired
    private AdminRepositoty adminRepositoty;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminEntity execute(AdminEntity adminEntity) {
        this.adminRepositoty.findByUsername(adminEntity.getUsername()).ifPresent((admin) -> {
            throw new AdminAlreadyExist();
        });

        var password = this.passwordEncoder.encode(adminEntity.getPassword());
        adminEntity.setPassword(password);

        return this.adminRepositoty.save(adminEntity);
    }

}
