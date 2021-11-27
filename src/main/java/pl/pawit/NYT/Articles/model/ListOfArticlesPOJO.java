package pl.pawit.NYT.Articles.model;

import lombok.Getter;

import java.util.List;

@Getter
public class ListOfArticlesPOJO {

    private String status;
    private int num_results;
    private List<ArticlePOJO> results;
}
