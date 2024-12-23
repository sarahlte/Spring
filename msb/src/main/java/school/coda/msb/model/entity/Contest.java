package school.coda.msb.model.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /* AUTO-INCREMENT */
    private Integer id;

    @Column(nullable = false)
    private Date startDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Game game;

    @ManyToOne
    private Player winner;

    @ManyToMany
    @JoinTable(name = "player_contest", joinColumns = { @JoinColumn(name = "contest_id") }, inverseJoinColumns = {
            @JoinColumn(name = "player_id") })
    private List<Player> players = new ArrayList<Player>();

    @Override
    public String toString() {
        String text = "Partie n°" + this.id + " de " + this.game.getTitle() + ", jouée le "
                + this.startDate.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (this.winner != null) {
            text += " et remportée par " + this.winner.getNickname();
        }
        return text;
    }

    public String getStartDateFR() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormatter.format(startDate);
    }
}
