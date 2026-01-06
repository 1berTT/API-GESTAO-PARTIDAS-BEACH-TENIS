package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.entities.MatchEntity;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.repositories.MatchRepository;
import br.com.humbertodamasceno.gestao_partidas_beach_tenis.exceptions.MatchAlreadyExist;

@Service
public class CreateMatchUseCase {

    @Autowired
    private MatchRepository matchRepository;

    public MatchEntity execute(MatchEntity matchEntity) {

        return this.matchRepository.save(matchEntity);
    }

}
