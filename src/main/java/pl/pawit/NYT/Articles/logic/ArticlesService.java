package pl.pawit.NYT.Articles.logic;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.pawit.NYT.Articles.adapter.NytApiConnector;
import pl.pawit.NYT.Articles.model.Article;
import pl.pawit.NYT.Articles.model.ArticlePOJO;
import pl.pawit.NYT.Articles.model.ListOfArticlesPOJO;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
public class ArticlesService {

    private NytApiConnector nytApiConnector;

    public ArticlesService(NytApiConnector nytApiConnector) {
        this.nytApiConnector = nytApiConnector;
    }

    public ListOfArticlesPOJO mapArticlesPojo(String section) {
        try {
            return nytApiConnector.createPOJOArticlesFromJson(section);
        } catch (IllegalArgumentException exception) {
            nytApiConnector.setResponseCode(HttpStatus.NOT_FOUND.value());
            System.out.println(exception.getMessage());
            throw exception;
        }

    }

    Article mapPOJOtoEntity(ArticlePOJO pojo){
        return Article.builder()
                .sectionType(pojo.getSection())
                .title(pojo.getTitle())
                .abstractField(pojo.getAbstractField())
                .byline(pojo.getByline()).build();
    }

    public List<Article> mapFromPojoToArticlesListEntity(String section) {
        return mapArticlesPojo(section).getResults()
                .stream()
                .map(this::mapPOJOtoEntity)
                .collect(Collectors.toList());
    }
}
