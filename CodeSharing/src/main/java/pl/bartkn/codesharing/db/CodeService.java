package pl.bartkn.codesharing.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartkn.codesharing.code.Code;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CodeService {

    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public Code findCodeById(UUID id) {
        Code founded = codeRepository.findCodeById(id);

        if (founded == null) {
            return null;
        }

        boolean timeFlag = founded.isTimeFlag();
        boolean viewsFlag = founded.isViewsFlag();

        System.out.println("Founded: " + founded);

        if (!timeFlag && !viewsFlag) {
            return founded;
        }

        if (timeFlag) {
            founded.updateTime(Math.abs(ChronoUnit.SECONDS.between(founded.getExpiryDate(), LocalDateTime.now())));
            int compareResult = LocalDateTime.now().compareTo(founded.getExpiryDate());
            if (compareResult == 1) {
                delete(founded);
                return null;
            }
        }

        if (viewsFlag) {
            long availableViews = founded.getViews();
            if (availableViews == 0) {
                delete(founded);
                return null;
            } else if (availableViews > 0) {
                founded.setViews(founded.getViews() - 1);
                save(founded);
            }
        }

        return founded;
    }

    public Code save(Code toSave) {
        return codeRepository.save(toSave);
    }

    public void delete(Code toDelete) {
        codeRepository.delete(toDelete);
    }

    public Long count() {
        return codeRepository.count();
    }

    public List<Code> getCodesWithoutRestrictions() {
        return codeRepository.findAllByTimeFlagFalseAndViewsFlagFalseOrderByDateDesc()
                .stream()
                .limit(10)
                .collect(Collectors.toList());
    }
}
