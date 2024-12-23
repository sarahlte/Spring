package school.coda.msb.model.entity.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import school.coda.msb.model.entity.Contest;
import school.coda.msb.model.entity.Player;

@Data
public class ContestDTO {
    private Integer id;
    private String start_date;
    private String name;
    private String game;
    private Integer game_id;
    private String winner;
    private Integer winner_id;
    private List<String> players;
    private List<Integer> players_id;

    public ContestDTO(Contest c) {
        this.id = c.getId();
        this.start_date = c.getStartDateFR();
        this.game = c.getGame().getTitle();
        this.game_id = c.getGame().getId();
        this.winner = c.getWinner() != null ? c.getWinner().getNickname() : "-";
        this.winner_id = c.getWinner() != null ? c.getWinner().getId() : null;
        this.players = c.getPlayers().stream().map(Player::getNickname).collect(Collectors.toList());
        this.players_id = c.getPlayers().stream().map(Player::getId).collect(Collectors.toList());
    }
}
