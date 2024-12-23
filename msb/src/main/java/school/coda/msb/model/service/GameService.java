package school.coda.msb.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.coda.msb.model.entity.Game;
import school.coda.msb.model.repository.GameRepository;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public List<Game> selectAll() {
        return (List<Game>) gameRepository.findAll();
    }

    public Game select(Integer id) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        return optionalGame.orElse(null);
    }

    public Game save(Game game) {
        return gameRepository.save(game);
    }

    public void delete(Integer id) {
        gameRepository.deleteById(id);
    }

    public void delete(Game game) {
        gameRepository.delete(game);
    }
}
