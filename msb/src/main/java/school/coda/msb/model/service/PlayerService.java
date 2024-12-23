package school.coda.msb.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.coda.msb.model.entity.Player;
import school.coda.msb.model.repository.PlayerRepository;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> selectAll() {
        return (List<Player>) playerRepository.findAll();
    }

    public Player select(Integer id) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        return optionalPlayer.orElse(null);
    }

    public Player save(Player Player) {
        return playerRepository.save(Player);
    }

    public void delete(Integer id) {
        playerRepository.deleteById(id);
    }

    public void delete(Player Player) {
        playerRepository.delete(Player);
    }
}
