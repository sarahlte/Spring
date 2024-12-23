package school.coda.msb.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import school.coda.msb.model.entity.Contest;

@Repository
public interface ContestRepository extends CrudRepository<Contest, Integer> {

}

