package br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.useCases;

import java.io.IOException;
import java.util.UUID;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.modules.match.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.humbertodamasceno.gestao_partidas_beach_tenis.exceptions.MatchNotFound;

@Service
public class DeleteMatchUseCase {

    @Autowired
    private MatchRepository matchRepository;

    public void execute(UUID id) throws IOException {
        var match = this.matchRepository.findById(id).orElseThrow(() -> {
            throw new MatchNotFound();
        });

        this.matchRepository.delete(match);
    }

}
