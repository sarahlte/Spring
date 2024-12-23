package school.coda.msb.model.entity.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import school.coda.msb.model.entity.Contest;
import school.coda.msb.model.entity.Game;

@Data
public class GameDTO {
    private Integer id;
    private String title;
    private Integer minPlayers;
    private Integer maxPlayers;
    private List<String> contests;

    public GameDTO(Game game) {
        this.id = game.getId();
        this.title = game.getTitle();
        this.minPlayers = game.getMinPlayers();
        this.maxPlayers = game.getMaxPlayers();
        this.contests = new ArrayList<String>();
        if (!game.getContests().isEmpty()) {
            for (Contest contest : game.getContests()) {
                this.contests
                        .add(String.format("Partie n°%d du %s", contest.getId(), contest.getStartDate().toString()));
            }
        }
    }
}
