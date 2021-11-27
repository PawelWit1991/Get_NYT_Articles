package pl.pawit.NYT.Articles.model;

import pl.pawit.NYT.Articles.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    List<Article> findAll();

    Optional<Article> findById(Integer id);

    <S extends Article> List<S> saveAll(Iterable<S> entities);

    boolean existsById(Integer integer);

    void deleteById(Integer id);

    void deleteAll();

    void deleteArticlesBySection(String section);

    List<Article> findArticlesBySection(String section);

    boolean existsBySection(String section);

}
