package pl.pawit.NYT.Articles.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pawit.NYT.Articles.model.SectionRepository;
import pl.pawit.NYT.Articles.model.Section;

import java.util.Optional;


@Repository
public interface SqlSectionRepository extends SectionRepository, JpaRepository<Section, Integer> {

    @Override
    Section save(Section section);

    @Override
    Optional<Section> findById(Integer id);

    @Override
    boolean existsById(Integer id);

    @Override
    void deleteById(Integer id);

    @Override
    void deleteAll();

}
