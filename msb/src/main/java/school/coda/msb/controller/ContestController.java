package school.coda.msb.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import school.coda.msb.model.entity.Contest;
import school.coda.msb.model.entity.Game;
import school.coda.msb.model.entity.Player;
import school.coda.msb.model.entity.dto.ContestDTO;
import school.coda.msb.model.service.ContestService;
import school.coda.msb.model.service.PlayerService;

@CrossOrigin // permet d'éviter les erreurs CORS
@RestController
public class ContestController {
    @Autowired
    ContestService contestService;

    @Autowired
    PlayerService ps;

    @GetMapping("/contests")
    public List<ContestDTO> list() {
        return contestService.selectAll().stream().map(c -> new ContestDTO(c)).collect(Collectors.toList());
    }

    @GetMapping("/contest/{id}")
    public ContestDTO contest(@PathVariable int id) {
        return new ContestDTO(contestService.select(id));
    }

    // public ContestDTO contest(@RequestBody Contest contest) {
    // return contestService.save(contest);
    // }

    /**
     * ? EXERCICE : Ecrire la méthode qui récupère les valeurs
     * ? d'un formulaire pour enregistrer une nouvelle partie (Contest)
     */
    @PostMapping("/contest")
    public ContestDTO add(@RequestParam("start_date") Date startDate,
            @RequestParam("game_id") Game game,
            @RequestParam("winner_id") Optional<Player> winner) {
        Contest contest = new Contest();
        contest.setGame(game);
        contest.setStartDate(startDate);
        contest.setWinner(winner.orElse(null));
        return new ContestDTO(contestService.save(contest));
    }

    @DeleteMapping("/contest/{id}")
    public Boolean delete(@PathVariable int id) {
        Contest contest = contestService.select(id);
        if (contest != null) {
            contestService.delete(contest);
            return true;
        }
        return false;
    }

    // Les modifications ne peuvent être faites que si la partie n'a pas encore eu
    // lieu sauf pour la désignation du vainqueur
    @PutMapping("/contest/{id}")
    public ContestDTO update(@PathVariable int id,
            @RequestBody Contest contest) {
        LocalDate now = LocalDate.now();
        LocalDate contestDate = contest.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (id == contest.getId() && now.isBefore(contestDate)) {
            // On vérifie si on a pas passé en paramètre une valeur pour le vainqueur et si
            // il a été passé en paramètre on le supprime pour empêcher la désignation avant
            // que la partie ait eu lieu
            if (contest.getWinner() != null) {
                contest.setWinner(null);
            }
            return new ContestDTO(contestService.save(contest));
        }
        return null;
    }

    // Pour ajouter ou supprimer un joueur d'une partie existante
    @PutMapping("/contest/{id}/player")
    public ContestDTO update(@PathVariable int id,
            @RequestParam Player player) {
        Contest contest = contestService.select(id);
        LocalDate now = LocalDate.now();
        LocalDate contestDate = contest.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (contest != null && now.isBefore(contestDate)) {
            List<Player> players = contest.getPlayers();
            Boolean exist = false;
            for (Player playerOfList : players) {
                if (playerOfList == player) {
                    exist = true;
                }
            }
            if (!exist) {
                players.add(player);
                contest.setPlayers(players);
            } else {
                players.remove(player);
                contest.setPlayers(players);
            }

            return new ContestDTO(contestService.save(contest));
        }
        return null;
    }

    // Pour ajouter plusieurs joueurs à une partie existante
    @PutMapping("/contest/{id}/players")
    public ContestDTO update(@PathVariable int id,
            @RequestParam List<Player> players) {
        Contest contest = contestService.select(id);
        LocalDate now = LocalDate.now();
        LocalDate contestDate = contest.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (contest != null && now.isBefore(contestDate)) {
            List<Player> playersContest = contest.getPlayers();
            for (Player player : players) {
                Boolean exist = false;
                for (Player playerContest : playersContest) {
                    if (playerContest == player) {
                        exist = true;
                    }
                }
                if (!exist) {
                    playersContest.add(player);
                }
            }
            contest.setPlayers(playersContest);
            contest = contestService.save(contest);
            // ContestDTO contestDTO = new ContestDTO(contest);
            return new ContestDTO(contest);
        }
        return null;
    }

    // Pour ajouter un gagnant si la partie n'en a pas encore
    // Uniquement si la partie a déja eu lieu
    @PutMapping("/contest/{id}/winner")
    public ContestDTO updateWinner(@PathVariable int id,
            @RequestParam Player player) {
        Contest contest = contestService.select(id);
        LocalDate now = LocalDate.now();
        LocalDate contestDate = contest.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (contest != null && contestDate.isBefore(now)) {
            if (contest.getWinner() == null) {
                List<Player> players = contest.getPlayers();
                Boolean exist = false;
                for (Player playerOfList : players) {
                    if (playerOfList == player) {
                        exist = true;
                    }
                }
                if (exist) {
                    contest.setWinner(player);
                }
            }

            return new ContestDTO(contestService.save(contest));
        }
        return null;
    }

}
