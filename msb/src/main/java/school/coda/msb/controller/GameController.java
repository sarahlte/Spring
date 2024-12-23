package school.coda.msb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import school.coda.msb.model.entity.Game;
import school.coda.msb.model.entity.dto.GameDTO;
import school.coda.msb.model.service.GameService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
public class GameController {
    @Autowired
    GameService gs;

    /*
     * Méthode pour une API :
     * - GET = READ
     * - POST = CREATE
     * - PUT = UPDATE
     * - DELETE
     */

    @GetMapping("/games")
    public List<GameDTO> list() {
        List<Game> list = gs.selectAll();
        List<GameDTO> listDTO = new ArrayList<GameDTO>();
        for (Game game : list) {
            listDTO.add(new GameDTO(game));
        }
        return listDTO;
    }

    @GetMapping("/game/{id}")
    public GameDTO game(@PathVariable Integer id) {
        GameDTO gameDTO = new GameDTO(gs.select(id));
        return gameDTO;
    }

    @PostMapping("/game")
    public GameDTO game(@RequestBody Game game) {
        GameDTO gameDTO = new GameDTO(gs.save(game));
        return gameDTO;
    }

    @PutMapping("/game/{id}")
    public GameDTO gamePut(@PathVariable Integer id, @RequestBody Game game) {
        if (id == game.getId()) {
            GameDTO gameDTO = new GameDTO(gs.save(game));
            return gameDTO;
        }
        return null;
    }

    @DeleteMapping("/game/{id}")
    public boolean delete(@PathVariable Integer id) {
        Game game = gs.select(id);
        if (game != null) {
            gs.delete(id);
            return true;
        }
        return false;
    }

    @DeleteMapping("/game")
    public boolean getMethodName(@RequestBody Game game) {
        gs.delete(game);
        return true;
    }

}
