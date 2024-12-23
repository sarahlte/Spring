package school.coda.msb.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import school.coda.msb.model.entity.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {

}
