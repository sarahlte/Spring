package school.coda.msb.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.coda.msb.model.entity.Contest;
import school.coda.msb.model.repository.ContestRepository;

@Service
public class ContestService {
    @Autowired
    private ContestRepository ContestRepository;

    public List<Contest> selectAll() {
        return (List<Contest>) ContestRepository.findAll();
    }

    public Contest select(Integer id) {
        Optional<Contest> optionalContest = ContestRepository.findById(id);
        return optionalContest.orElse(null);
    }

    public Contest save(Contest Contest) {
        return ContestRepository.save(Contest);
    }

    public void delete(Integer id) {
        ContestRepository.deleteById(id);
    }

    public void delete(Contest Contest) {
        ContestRepository.delete(Contest);
    }
}
