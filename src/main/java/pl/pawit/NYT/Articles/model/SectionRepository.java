package pl.pawit.NYT.Articles.model;

import pl.pawit.NYT.Articles.model.Section;

import java.util.List;
import java.util.Optional;

public interface SectionRepository {

    Section save(Section section);

    Optional<Section> findById(Integer id);

    List<Section> findAll();

    boolean existsById(Integer id);

    void deleteById(Integer id);

    void deleteAll();
}
