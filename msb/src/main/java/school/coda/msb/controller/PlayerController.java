package school.coda.msb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import school.coda.msb.model.entity.Player;
import school.coda.msb.model.entity.dto.PlayerDTO;
import school.coda.msb.model.service.PlayerService;

@RestController
@CrossOrigin
public class PlayerController {
    @Autowired
    PlayerService ps;

    /*
     * Méthode pour une API :
     * - GET = READ
     * - POST = CREATE
     * - PUT = UPDATE
     * - DELETE
     */

    @GetMapping("/players")
    public List<PlayerDTO> list() {
        List<Player> list = ps.selectAll();
        List<PlayerDTO> listDTO = new ArrayList<PlayerDTO>();
        for (Player p : list) {
            listDTO.add(new PlayerDTO(p));
        }
        return listDTO;
    }

    @GetMapping("/player/{id}")
    public Player Player(@PathVariable Integer id) {
        // PlayerDTO playerDTO = new PlayerDTO(ps.select(id));
        return ps.select(id);
    }

    @PostMapping("/player")
    public Player Player(@RequestBody Player player) {
        // PlayerDTO playerDTO = new PlayerDTO(ps.save(player));
        return ps.save(player);
    }

    @PutMapping("/player/{id}")
    public Player PlayerPut(@PathVariable Integer id, @RequestBody Player player) {
        if (id == player.getId()) {
            // PlayerDTO playerDTO = new PlayerDTO(ps.save(player));
            return ps.save(player);
        }
        return null;

    }

    @DeleteMapping("/player/{id}")
    public boolean delete(@PathVariable Integer id) {
        Player player = ps.select(id);
        if (player != null) {
            ps.delete(id);
            return true;
        }
        return false;
    }

    @DeleteMapping("/player")
    public boolean getMethodName(@RequestBody Player player) {
        ps.delete(player);
        return true;
    }
}
