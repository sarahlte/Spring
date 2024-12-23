package school.coda.msb.model.entity.dto;

import lombok.Data;
import school.coda.msb.model.entity.Contest;
import school.coda.msb.model.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Data
public class PlayerDTO {
    private Integer id;
    private String email;
    private String nickname;
    private List<String> wins = new ArrayList<>();
    private List<String> contests = new ArrayList<>();

    public PlayerDTO(Player p) {
        this.id = p.getId();
        this.email = p.getEmail();
        this.nickname = p.getNickname();

        if (p.getWins() != null) {
            for (Contest c : p.getWins()) {
                this.wins.add(c.toString());
            }
            // this.wins =
            // p.getWins().stream().map(Contest::toString).collect(Collectors.toList());
        }

        if (p.getContests() != null) {
            for (Contest c : p.getContests()) {
                this.contests.add(c.toString());
            }
            // this.contests =
            // p.getContests().stream().map(Contest::toString).collect(Collectors.toList());
        }
    }
}