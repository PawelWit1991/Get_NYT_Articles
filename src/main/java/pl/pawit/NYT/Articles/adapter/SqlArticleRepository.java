package pl.pawit.NYT.Articles.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pawit.NYT.Articles.model.ArticleRepository;
import pl.pawit.NYT.Articles.model.Article;

import java.util.List;


@Repository
public interface SqlArticleRepository extends ArticleRepository, JpaRepository<Article, Integer> {

    @Override
    <S extends Article> List<S> saveAll(Iterable<S> entities);

    @Override
    boolean existsById(Integer integer);

    @Override
    void deleteById(Integer id);

    @Override
    void deleteAll();

    void deleteArticlesBySection(String section);

    List<Article> findArticlesBySection(String section);

    boolean existsBySection(String section);
}
