package school.coda.msb.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /* AUTO-INCREMENT */
    private Integer id;

    @Column(length = 50, nullable = false)
    private String title;

    private Integer minPlayers;
    private Integer maxPlayers;

    @OneToMany(mappedBy = "game")
    private List<Contest> contests;
}
