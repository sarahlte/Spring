package school.coda.msb.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import school.coda.msb.model.entity.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {

}
