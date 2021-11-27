package pl.pawit.NYT.Articles.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class ArticlePOJO {

    private String section;
    private String title;
    @SerializedName("abstract")
    private String abstractField;
    private String byline;
}
