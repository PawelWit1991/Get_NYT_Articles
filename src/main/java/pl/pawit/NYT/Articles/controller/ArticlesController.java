package pl.pawit.NYT.Articles.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pawit.NYT.Articles.model.ArticleRepository;
import pl.pawit.NYT.Articles.adapter.SqlArticleRepository;
import pl.pawit.NYT.Articles.logic.ArticlesService;
import pl.pawit.NYT.Articles.model.Article;

import java.net.URI;
import java.util.List;

@RestController
public class ArticlesController {

    private ArticlesService articlesService;
    private ArticleRepository articleRepository;
    private static final Logger logger = LoggerFactory.getLogger(SqlArticleRepository.class);


    public ArticlesController(ArticlesService articlesService, ArticleRepository articleRepository) {
        this.articlesService = articlesService;
        this.articleRepository = articleRepository;
    }


    @PostMapping("/saveAll/{section}")
    ResponseEntity<?> saveAllTasks(@PathVariable String section) {
        logger.info("articles with given section saved");
        List<Article> articles = articleRepository.saveAll(articlesService.mapFromPojoToArticlesListEntity(section));
        return ResponseEntity.created(URI.create("200")).body(articles);
    }

    @GetMapping("/articles/{id}")
    ResponseEntity<Article> readById(@PathVariable int id){
        logger.info("article with given id returned");
        return articleRepository.findById(id)
                .map(article -> ResponseEntity.ok(article))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/articles")
    ResponseEntity<List<Article>> readAll(){
        logger.info("All articles showed");
        return ResponseEntity.ok(articleRepository.findAll());
    }

    @GetMapping("/articles/section/{section}")
    ResponseEntity<List<Article>> readBySection(@PathVariable String section){
        logger.info("All articles in given section showed");
        return ResponseEntity.ok(articleRepository.findArticlesBySection(section));
    }

    @DeleteMapping("/articles/{id}")
    ResponseEntity<?> deleteById(@PathVariable int id){
        if(!articleRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        articleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/articles/section/{section}")
    ResponseEntity<?> deleteById(@PathVariable String section){
        if(!articleRepository.existsBySection(section)){
            return ResponseEntity.notFound().build();
        }
        articleRepository.deleteArticlesBySection(section);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/articles")
    ResponseEntity<?> deleteAll(){
        articleRepository.deleteAll();
        return ResponseEntity.ok().build();
    }


}
