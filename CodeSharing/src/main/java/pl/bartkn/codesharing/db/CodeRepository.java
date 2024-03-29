package pl.bartkn.codesharing.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bartkn.codesharing.code.Code;

import java.util.List;
import java.util.UUID;

@Repository
public interface CodeRepository extends CrudRepository<Code, UUID> {
    Code findCodeById(UUID id);
    List<Code> findAllByOrderByDateDesc();
    List<Code> findAllByTimeFlagFalseAndViewsFlagFalseOrderByDateDesc();
}
